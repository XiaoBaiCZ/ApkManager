package cc.xiaobaicz.apkmanager.fragment

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import cc.xiaobaicz.apkmanager.databinding.FragmentMainBinding

/**
 * 首页
 */
class MainFragment : BaseFragment() {

    private val bind by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    /**
     * 回退操作
     */
    private val onBackHolder = object {
        var time = 0L
        val limit = 1000L
        fun onBack() {
            if (SystemClock.elapsedRealtime() - time < limit) {
                requireActivity().finish()
                return
            }
            time = SystemClock.elapsedRealtime()
            iToast?.makeToast("再按一次退出")?.show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            onBackHolder.onBack()
        }
    }

}