package cc.xiaobaicz.apkmanager.fragment

import android.content.pm.PackageInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import cc.xiaobaicz.apkmanager.databinding.FragmentPkgInfoBinding
import cc.xiaobaicz.apkmanager.global.KEY_PKG_INFO
import cc.xiaobaicz.apkmanager.viewmodel.PkgInfoViewModel

/**
 * 安装包详细信息页面
 */
class PkgInfoFragment : BaseFragment() {

    private lateinit var bind: FragmentPkgInfoBinding

    private val model by viewModels<PkgInfoViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentPkgInfoBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.model = model
        bind.lifecycleOwner = this

        arguments?.apply {
            println(getParcelable<PackageInfo>(KEY_PKG_INFO))
        }

        model.configToolBar(requireActivity())
    }

}