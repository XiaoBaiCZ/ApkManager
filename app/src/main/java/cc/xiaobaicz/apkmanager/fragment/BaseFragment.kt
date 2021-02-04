package cc.xiaobaicz.apkmanager.fragment

import androidx.fragment.app.Fragment
import cc.xiaobaicz.apkmanager.util.IToast

/**
 * 基础页面
 */
abstract class BaseFragment : Fragment() {

    /**
     * 获取总页面，如非总页面则返回NULL
     */
    protected val iToast: IToast? get() {
        val activity = requireActivity()
        if (activity !is IToast) {
            return null
        }
        return activity
    }

}