package cc.xiaobaicz.apkmanager.entity

import android.view.View

/**
 * 工具栏配置信息
 */
class ToolBar {

    var sysUITop: Float = 0f

    var title: String = ""

    var height: Float = 0f

    var iconL: Int = 0
    var iconR: Int = 0

    var isVisibleL = View.VISIBLE
    var isVisibleR = View.GONE

    var onClickL: View.OnClickListener? = null
    var onClickR: View.OnClickListener? = null

    var isSelectedL = false
    var isSelectedR = false

    fun onClickL(view: View) {
        onClickL?.onClick(view)
    }

    fun onClickR(view: View) {
        onClickR?.onClick(view)
    }

}