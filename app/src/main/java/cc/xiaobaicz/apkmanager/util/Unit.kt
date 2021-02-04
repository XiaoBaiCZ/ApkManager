package cc.xiaobaicz.apkmanager.util

import android.content.res.Resources
import android.util.TypedValue

/**
 * dp 2 px
 */
val Number.dp: Float get() {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)
}

/**
 * sp 2 px
 */
val Number.sp: Float get() {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), Resources.getSystem().displayMetrics)
}