package cc.xiaobaicz.apkmanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import cc.xiaobaicz.apkmanager.R
import cc.xiaobaicz.apkmanager.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 闪屏页面
 */
class SplashFragment : BaseFragment() {

    private lateinit var bind: FragmentSplashBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gotoMain()

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            //拦截返回键
        }
    }

    //去首页
    private fun gotoMain() {
        lifecycleScope.launch {
            delay(3000)
            //跳转首页
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        }
    }

}