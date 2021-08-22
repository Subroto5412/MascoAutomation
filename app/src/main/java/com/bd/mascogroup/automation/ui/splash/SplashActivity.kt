package com.bd.mascogroup.automation.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.core.view.isGone
import androidx.core.view.isVisible
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
       /* Handler().postDelayed(
                {
                    openMainActivity()
                    finish()
                }, 3 * 1000)*/

        val animationZoom = AnimationUtils.loadAnimation(this, R.anim.zoom)
        activity_spalsh_down_logo_im2.startAnimation(animationZoom)



        /*if (density > 400) {
            textNameAnimationXXHDI()
        } else {
            when (density) {
                DisplayMetrics.DENSITY_LOW -> {
                    textNameAnimation()
                }
                DisplayMetrics.DENSITY_MEDIUM -> {
                    textNameAnimation()
                }
                DisplayMetrics.DENSITY_HIGH -> {
                    textNameAnimation()
                }
                DisplayMetrics.DENSITY_XHIGH -> {
                    textNameAnimation()
                }
                DisplayMetrics.DENSITY_XXHIGH -> {
                    textNameAnimationXXHDI()
                }
                DisplayMetrics.DENSITY_XXXHIGH -> {
                    textNameAnimationXXHDI()
                }
            }
        }*/

        Handler().postDelayed(
                {
                    val animationZoom1 = AnimationUtils.loadAnimation(this, R.anim.zoom)
                    activity_spalsh_down_logo_im2.startAnimation(animationZoom1)
                    Log.e("---------","------nnn-------")
                }, 1000)

       /*  Handler().postDelayed(
                  {
                      activity_spalsh_down_logo_im2.isGone = true
                      activity_spalsh_down_logo_im3.isGone = false
                  }, 500)
*/

        Handler().postDelayed(
                  {
                      Log.e("---------","------da-------")
                      val animationZoom2 = AnimationUtils.loadAnimation(this, R.anim.zoom2)
                      activity_spalsh_down_logo_im2.startAnimation(animationZoom2)
                  }, 2000)

        Handler().postDelayed(
                {
                    Log.e("---------","------da-------")
                    val animationZoom3 = AnimationUtils.loadAnimation(this, R.anim.zoom3)
                    activity_spalsh_down_logo_im2.startAnimation(animationZoom3)
                }, 3000)


//        val animationZoom2 = AnimationUtils.loadAnimation(this, R.anim.zoom2)
//        activity_spalsh_down_logo_im2.startAnimation(animationZoom2)
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
     /*   val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)*/
    }

    //    override fun exitApplication() {
//        finish()
//    }
    override fun androidInjector(): AndroidInjector<Any>? {
        return fragmentDispatchingAndroidInjector
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SplashActivity::class.java)
        }
    }
}