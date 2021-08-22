package com.bd.mascogroup.automation.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityLoginBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.production_management.ProductionManagementActivity
import com.bd.mascogroup.automation.ui.signup.SignupActivity
import com.bd.mascogroup.automation.utils.AppUtils
import com.google.android.material.card.MaterialCardView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_login.*
import java.net.URL
import javax.inject.Inject

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), ILoginNavigator,
        HasAndroidInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    override lateinit var viewModel: LoginViewModel
        internal set
    private var mActivityLoginBinding: ActivityLoginBinding? = null

    @Inject
    lateinit var mLoginViewModel: LoginViewModel



    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_login


    override fun login() {
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLoginBinding = viewDataBinding
        viewModel.navigator = this
        activity_login_signup_tv.setOnClickListener {
            val intent = SignupActivity.newIntent(this@LoginActivity)
            startActivity(intent)
        }

        signInBtn.setOnClickListener {
           /* val intent = ProductionManagementActivity.newIntent(this@LoginActivity)
            startActivity(intent)*/
            openSignupActivity()
        }
        activity_login_forgot_password_tv.setOnClickListener {
            val intent = SignupActivity.newIntent(this@LoginActivity)
            startActivity(intent)
        }
        viewModel.setup(this,activity_login_user_id_et,activity_login_password_et, activity_login_logo_im,activity_login_user_cl,
                activity_login_user_name_tv, activity_login_unit_name_tv, activity_login_signin_btn,activity_login_signin_btn_hide)
    }

     fun openSignupActivity() {
        val intent = HomeActivity.newIntent(this@LoginActivity)
        startActivity(intent)
//        finish()
    }

    override fun androidInjector(): AndroidInjector<Any>? {
        return fragmentDispatchingAndroidInjector
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}
