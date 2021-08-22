package com.bd.mascogroup.automation.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isGone
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivitySignupBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.login.LoginActivity
import com.bd.mascogroup.automation.ui.otp.OTPActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import javax.inject.Inject

class SignupActivity : BaseActivity<ActivitySignupBinding, SignupViewModel>(), ISignupNavigator{


    @Inject
    override lateinit var viewModel: SignupViewModel

    private var mActivitySignupBinding: ActivitySignupBinding? = null

    @Inject
    lateinit var mSignupViewModel: SignupViewModel



    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_signup



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivitySignupBinding = viewDataBinding
        viewModel.navigator = this

        signInBtnmaterialCardView.setOnClickListener {
            openOtpActivity()
        }
        activity_signin_tv.setOnClickListener {
            val intent = LoginActivity.newIntent(this@SignupActivity)
            startActivity(intent)
        }
        setup()
    }

    fun setup() {

        activity_signup_user_id_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.toString().equals("")){
                    activity_signup_user_id_et.setBackgroundResource(R.drawable.input_field_white_bg)
                    signInBtnmaterialCardView.isGone = false
                    signInBtnmaterialCardViewHide.isGone = true
                }else{
                    activity_signup_user_id_et.setBackgroundResource(R.drawable.input_filed_bg)
                    signInBtnmaterialCardViewHide.isGone = false
                    signInBtnmaterialCardView.isGone = true
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }

    fun openOtpActivity() {
        val intent = OTPActivity.newIntent(this@SignupActivity)
        startActivity(intent)
//        finish()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SignupActivity::class.java)
        }
    }
}