package cc.xiaobaicz.apkmanager.util

import android.view.View
import cc.xiaobaicz.apkmanager.R
import cc.xiaobaicz.apkmanager.entity.ToolBar

/**
 * 配置返回按钮
 */
fun ToolBar.configGoBack(click: View.OnClickListener) {
    this.isVisibleL = View.VISIBLE
    this.iconL = R.drawable.ic_back_56
    this.onClickL = click
}