package cc.xiaobaicz.apkmanager.util

import com.google.android.material.snackbar.Snackbar

/**
 * 创建提示组件
 */
interface IToast {
    fun makeToast(msg: String, duration: Int = Snackbar.LENGTH_SHORT): Snackbar
    fun makeToast(resId: Int, duration: Int = Snackbar.LENGTH_SHORT): Snackbar
}