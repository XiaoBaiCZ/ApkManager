package cc.xiaobaicz.apkmanager.global

import android.content.pm.PackageInfo
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.updatePaddingRelative
import androidx.databinding.BindingAdapter
import cc.xiaobaicz.apkmanager.GlideApp
import cc.xiaobaicz.apkmanager.entity.ToolBar
import cc.xiaobaicz.apkmanager.util.dp

@BindingAdapter("app:pkg_icon")
fun bindImage(view: ImageView, info: PackageInfo) {
    GlideApp.with(view).load(info.applicationInfo.loadIcon(view.context.packageManager)).into(view)
}

@BindingAdapter("app:apk_name")
fun bindText(view: TextView, info: PackageInfo) {
    val label = info.applicationInfo.loadLabel(view.context.packageManager)
    view.text = label
}

@BindingAdapter("app:auto_height")
fun bindAutoHeight(view: View, toolBar: ToolBar?) {
    toolBar ?: return
    view.updatePaddingRelative(top = toolBar.sysUITop.toInt())
    view.layoutParams.height = (toolBar.sysUITop + 56.dp).toInt()
}

@BindingAdapter("app:res_id")
fun bindImageViewSrc(view: ImageView, resId: Int) {
    view.setImageResource(resId)
}
