package com.bd.mascogroup.automation.ui.otp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.EditText
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityOtpBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.login.LoginActivity
import com.bd.mascogroup.automation.ui.signup.SignupActivity
import com.bd.mascogroup.automation.ui.signup.SignupViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_otp.*
import javax.inject.Inject

class OTPActivity : BaseActivity<ActivityOtpBinding, OTPViewModel>(), IOTPNavigator {

    private var mActivityOtpBinding: ActivityOtpBinding? = null

    @Inject
    lateinit var mOTPViewModel: OTPViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_otp

    override val viewModel: OTPViewModel
        get() {
            return mOTPViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityOtpBinding = viewDataBinding
        viewModel.navigator = this

        viewModel.doVerifyOTP(this,activity_otp_n1_value_text,otp_one_ed, otp_two_ed, otp_three_ed, otp_four_ed,activity_otp_verified_im)
        viewModel.setup(this,activity_otp_password_et, activity_otp_re_password_et, activity_password_verified_im, activity_otp_signup_btn,activity_otp_signup_btn_hide)

        activity_otp_signIn_tv.setOnClickListener {
            val intent = LoginActivity.newIntent(this@OTPActivity)
            startActivity(intent)
            finish()
        }

        activity_otp_forgot_resend_otp_tv.setOnClickListener {
            viewModel.doReSendOTP(this)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, OTPActivity::class.java)
        }
    }

    override fun openHomeScreen(){
        val intent = HomeActivity.newIntent(this@OTPActivity)
        startActivity(intent)
        finish()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            val intent = SignupActivity.newIntent(this@OTPActivity)
            startActivity(intent)
            finish()

        }
        return super.onKeyDown(keyCode, event)
    }

}