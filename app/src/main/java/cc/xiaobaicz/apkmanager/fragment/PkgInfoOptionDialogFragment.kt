package cc.xiaobaicz.apkmanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cc.xiaobaicz.apkmanager.R
import cc.xiaobaicz.apkmanager.databinding.DialogPkgInfoOptionBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * 包信息操作弹窗
 */
class PkgInfoOptionDialogFragment(val option: IOption) : BottomSheetDialogFragment() {

    private lateinit var bind: DialogPkgInfoOptionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_pkg_info_option, container, false).apply {
            bind = DialogPkgInfoOptionBinding.bind(this)
            bind.option = option
        }
    }

    /**
     * 选项操作接口
     */
    interface IOption {

        /**
         * 卸载
         */
        fun onUninstall(v: View)

        /**
         * 导出
         */
        fun onExport(v: View)

        /**
         * 取消
         */
        fun onCancel(v: View)

    }

}