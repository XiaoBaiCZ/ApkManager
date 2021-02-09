package cc.xiaobaicz.apkmanager.viewmodel

import android.app.Activity
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import cc.xiaobaicz.apkmanager.R
import cc.xiaobaicz.apkmanager.entity.ToolBar
import cc.xiaobaicz.apkmanager.global.KEY_IS_SHOW_SYS_APK
import cc.xiaobaicz.apkmanager.util.dp
import cc.xiaobaicz.apkmanager.util.sysUiSize
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

/**
 * 首页视图模型
 */
class MainViewModel : BaseViewModel() {

//    private val cache = LruCache<PackageInfo, >

    //安装包数据
    val packageInfos = ArrayList<Any>()

    //工具栏数据
    val toolBar = MutableLiveData<ToolBar>()

    //系统Apk
    val isShowSysApk = MutableLiveData(MMKV.defaultMMKV()?.decodeBool(KEY_IS_SHOW_SYS_APK, false) ?: false)

    private fun isShowSysApk() = isShowSysApk.value ?: false

    //配置工具栏
    fun configToolBar(activity: Activity) {
        activity.sysUiSize { _, t, _, _ ->
            toolBar.postValue(ToolBar().apply {
                title = activity.getString(R.string.app_name)
                isVisibleL = View.GONE
                sysUITop = t.toFloat()
                height = t.toFloat() + 56.dp
                iconR = R.drawable.ic_system_56
                isVisibleR = View.VISIBLE
                isSelectedR = isShowSysApk()
                onClickR = View.OnClickListener {
                    MMKV.defaultMMKV()?.encode(KEY_IS_SHOW_SYS_APK, !isShowSysApk())
                    isShowSysApk.value = !isShowSysApk()
                    it.isSelected = isShowSysApk()
                }
            })
        }
    }

    /**
     * 扫描所有APK
     */
    suspend fun scanApk(pm: PackageManager?): DiffUtil.DiffResult? {
        pm ?: return null
        if (packageInfos.isNotEmpty()) {
            return null
        }
        return refresh(pm)
    }

    /**
     * 刷新APK数据
     */
    suspend fun refresh(pm: PackageManager?) = suspendCancellableCoroutine<DiffUtil.DiffResult?> {
        viewModelScope.launch(Dispatchers.IO) {
            pm ?: return@launch
            //异步查找数据
            val old = ArrayList(packageInfos)
            val res = pm.getInstalledPackages(PackageManager.GET_SIGNATURES).filter {
                return@filter isShowSysApk() || (it.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0
            }
            withContext(Dispatchers.Main) {
                //主线程调整数据
                packageInfos.clear()
                packageInfos.addAll(res)
                val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                    override fun getOldListSize(): Int = old.size

                    override fun getNewListSize(): Int = packageInfos.size

                    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                        return (old[oldItemPosition] as PackageInfo).packageName == (packageInfos[newItemPosition] as PackageInfo).packageName
                    }

                    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                        return (old[oldItemPosition] as PackageInfo).packageName == (packageInfos[newItemPosition] as PackageInfo).packageName
                    }
                })
                it.resume(result)
            }
        }
    }


}