package cc.xiaobaicz.apkmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cc.xiaobaicz.apkmanager.databinding.ActivityAppBinding
import cc.xiaobaicz.apkmanager.util.IToast
import cc.xiaobaicz.apkmanager.util.setStatusBarColor
import cc.xiaobaicz.apkmanager.util.transparentStatusBar
import com.google.android.material.snackbar.Snackbar

/**
 * 总页面
 */
class AppActivity : AppCompatActivity(), IToast {

    private val bind by lazy {
        ActivityAppBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        //透明状态栏
        setStatusBarColor(0x33000000)
        transparentStatusBar()
    }

    override fun makeToast(msg: String, duration: Int): Snackbar {
        return Snackbar.make(bind.rootView, msg, duration)
    }

    override fun makeToast(resId: Int, duration: Int): Snackbar {
        return Snackbar.make(bind.rootView, resId, duration)
    }

}