package cc.xiaobaicz.apkmanager.util

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

/**
 * 系统UI高度接口
 */
fun Activity.sysUiSize(func: ((Int, Int, Int, Int)->Unit)? = null) {
    window.decorView.setOnApplyWindowInsetsListener { v, insets ->
        func?.invoke(insets.systemWindowInsetLeft, insets.systemWindowInsetTop, insets.systemWindowInsetRight, insets.systemWindowInsetBottom)
        return@setOnApplyWindowInsetsListener v.onApplyWindowInsets(insets)
    }
}

/**
 * 隐藏状态栏
 */
fun Activity.hideStatusBar() {
    val decorView = window.decorView
    configStatusBar9_0(window)
    decorView.systemUiVisibility = getLightModeFlag(window) or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
}

/**
 * 隐藏导航栏
 */
fun Activity.hideNavigationBar() {
    val decorView = window.decorView
    decorView.systemUiVisibility = getLightModeFlag(window) or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
}

/**
 * 透明状态栏
 */
fun Activity.transparentStatusBar() {
    val decorView = window.decorView
    decorView.systemUiVisibility = getLightModeFlag(window) or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
}

/**
 * 透明导航栏
 */
fun Activity.transparentNavigationBar() {
    val decorView = window.decorView
    decorView.systemUiVisibility = getLightModeFlag(window) or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
}

/**
 * 透明全屏 ps：透明状态栏 & 导航栏
 */
fun Activity.transparentScreen() {
    val decorView = window.decorView
    decorView.systemUiVisibility = getLightModeFlag(window) or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
}

/**
 * 全屏 ps：隐藏状态栏 & 导航栏
 */
fun Activity.fullScreen() {
    val decorView = window.decorView
    configStatusBar9_0(window)
    decorView.systemUiVisibility = getLightModeFlag(window) or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
}

/**
 * 修改状态栏颜色
 */
fun Activity.setStatusBarColor(color: Int) {
    window.statusBarColor = color
}

/**
 * 修改导航栏颜色
 */
fun Activity.setNavigationBarColor(color: Int) {
    window.navigationBarColor = color
}

/**
 * 设置亮色状态栏（ps：黑色前景）
 */
@TargetApi(Build.VERSION_CODES.M)
fun Activity.setLightStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

/**
 * 设置亮色导航栏（ps：黑色前景）
 */
@TargetApi(Build.VERSION_CODES.M)
fun Activity.setLightNavigationBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
    }
}

/**
 * 适配状态栏安卓9
 */
@TargetApi(Build.VERSION_CODES.P)
private fun configStatusBar9_0(window: Window) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val attr = window.attributes
        attr.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        window.attributes = attr
    }
}

/**
 * 获取状态栏，导航栏亮度模式
 * @since 0.3.2
 */
@TargetApi(Build.VERSION_CODES.M)
private fun getLightModeFlag(window: Window): Int {
    var flag = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        flag = flag or (window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        flag = flag or (window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR)
    }
    return flag
}