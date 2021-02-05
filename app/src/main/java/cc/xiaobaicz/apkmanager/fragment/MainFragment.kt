package cc.xiaobaicz.apkmanager.fragment

import android.content.pm.PackageInfo
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import cc.xiaobaicz.apkmanager.R
import cc.xiaobaicz.apkmanager.databinding.FragmentMainBinding
import cc.xiaobaicz.apkmanager.databinding.ItemApkBinding
import cc.xiaobaicz.apkmanager.global.KEY_PKG_INFO
import cc.xiaobaicz.apkmanager.viewmodel.MainViewModel
import cc.xiaobaicz.recyclerview.extend.config
import kotlinx.coroutines.launch

/**
 * 首页
 */
class MainFragment : BaseFragment() {

    private lateinit var bind: FragmentMainBinding

    private val model by viewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentMainBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.model = model
        bind.lifecycleOwner = this

        //配置列表
        bind.listApk.config(model.packageInfos) {
            //元素配置
            addType<PackageInfo, ApkViewHolder>(R.layout.item_apk) { d, h, _, _ ->
                h.bind.info = d
                h.bind.executePendingBindings()
                if (!h.bind.root.hasOnClickListeners()) {
                    //点击事件
                    h.bind.root.setOnClickListener {
                        val bundle = Bundle()
                        bundle.putParcelable(KEY_PKG_INFO, model.packageInfos[h.adapterPosition] as PackageInfo)
                        findNavController().navigate(R.id.action_mainFragment_to_pkgInfoFragment, bundle)
                    }
                }
            }
        }

        //动画
        bind.listApk.itemAnimator = DefaultItemAnimator()

        //刷新
        bind.refresh.setOnRefreshListener {
            refresh()
        }

        //显示系统APK
        model.isShowSysApk.observe(viewLifecycleOwner) {
            refresh()
        }

        //配置工具栏
        model.configToolBar(requireActivity())

        //双击退出
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            onBackHolder.onBack()
        }
    }

    private fun refresh() {
        lifecycleScope.launch {
            model.refresh(requireActivity().packageManager)?.apply {
                dispatchUpdatesTo(bind.listApk.adapter!!)
                bind.refresh.isRefreshing = false
            }
        }
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

    class ApkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bind by lazy {
            ItemApkBinding.bind(itemView)
        }
    }

}