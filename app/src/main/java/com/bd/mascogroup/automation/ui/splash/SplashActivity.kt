package com.bd.mascogroup.automation.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivitySplashBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.login.LoginActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), ISplashNavigator,
        HasAndroidInjector {

    override val viewModel: SplashViewModel
        get() = mSplashViewModel

    @Inject
    lateinit var mSplashViewModel: SplashViewModel

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this

        val animationZoom = AnimationUtils.loadAnimation(this, R.anim.zoom)
        activity_spalsh_down_logo_im2.startAnimation(animationZoom)

        Handler().postDelayed(
                {
                    val animationZoom1 = AnimationUtils.loadAnimation(this, R.anim.zoom)
                    activity_spalsh_down_logo_im2.startAnimation(animationZoom1)
                }, 1000)

        Handler().postDelayed(
                {
                    val animationZoom2 = AnimationUtils.loadAnimation(this, R.anim.zoom2)
                    activity_spalsh_down_logo_im2.startAnimation(animationZoom2)
                }, 2000)

        Handler().postDelayed(
                {
                    val animationZoom3 = AnimationUtils.loadAnimation(this, R.anim.zoom3)
                    activity_spalsh_down_logo_im2.startAnimation(animationZoom3)
                }, 3000)

        Handler().postDelayed({
            viewModel.startSeeding()
            finish()
        }, 4000)

    }

    override fun openMainActivity() {
        val intent = LoginActivity.newIntent(this@SplashActivity)
        startActivity(intent)
        finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    override fun androidInjector(): AndroidInjector<Any>? {
        return fragmentDispatchingAndroidInjector
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SplashActivity::class.java)
        }
    }
}