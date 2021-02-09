package cc.xiaobaicz.apkmanager.viewmodel

import android.app.Activity
import android.content.pm.PackageInfo
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import cc.xiaobaicz.apkmanager.R
import cc.xiaobaicz.apkmanager.entity.ToolBar
import cc.xiaobaicz.apkmanager.fragment.PkgInfoOptionDialogFragment
import cc.xiaobaicz.apkmanager.util.configGoBack
import cc.xiaobaicz.apkmanager.util.dp
import cc.xiaobaicz.apkmanager.util.sysUiSize

/**
 * 安装包信息视图模型
 */
class PkgInfoViewModel : BaseViewModel() {

    //工具栏数据
    val toolBar = MutableLiveData<ToolBar>()

    //APK
    lateinit var pkg: PackageInfo

    //选项对话框
    private lateinit var optionDialog: PkgInfoOptionDialogFragment

    //选项操作
    private val option = object : PkgInfoOptionDialogFragment.IOption {
        override fun onUninstall(v: View) {

        }

        override fun onExport(v: View) {

        }

        override fun onCancel(v: View) {
            optionDialog.dismissAllowingStateLoss()
        }
    }

    //配置工具栏
    fun configToolBar(activity: Activity) {
        activity.sysUiSize { _, t, _, _ ->
            toolBar.postValue(ToolBar().apply {
                title = activity.getString(R.string.pkg_info)
                isVisibleL = View.GONE
                sysUITop = t.toFloat()
                height = t.toFloat() + 56.dp
                configGoBack {
                    activity.findNavController(R.id.nav_host).popBackStack()
                }
                configOption(this, activity)
            })
        }
    }

    //配置选项按钮
    private fun configOption(toolBar: ToolBar, activity: Activity) {
        toolBar.isVisibleR = View.VISIBLE
        toolBar.iconR = R.drawable.ic_options_56
        toolBar.onClickR = View.OnClickListener {
            if (activity !is AppCompatActivity) {
                return@OnClickListener
            }
            optionDialog = PkgInfoOptionDialogFragment(option).apply {
                show(activity.supportFragmentManager, "option")
            }
        }
    }

}