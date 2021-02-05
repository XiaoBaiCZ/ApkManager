package cc.xiaobaicz.apkmanager.viewmodel

import android.app.Activity
import android.view.View
import androidx.lifecycle.MutableLiveData
import cc.xiaobaicz.apkmanager.R
import cc.xiaobaicz.apkmanager.entity.ToolBar
import cc.xiaobaicz.apkmanager.util.dp
import cc.xiaobaicz.apkmanager.util.sysUiSize

/**
 * 安装包信息视图模型
 */
class PkgInfoViewModel : BaseViewModel() {

    //工具栏数据
    val toolBar = MutableLiveData<ToolBar>()

    //配置工具栏
    fun configToolBar(activity: Activity) {
        activity.sysUiSize { _, t, _, _ ->
            toolBar.postValue(ToolBar().apply {
                title = activity.getString(R.string.pkg_info)
                isVisibleL = View.GONE
                sysUITop = t.toFloat()
                height = t.toFloat() + 56.dp
            })
        }
    }

}