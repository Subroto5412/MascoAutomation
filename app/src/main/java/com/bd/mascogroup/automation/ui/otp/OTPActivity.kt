package com.bd.mascogroup.automation.ui.otp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isGone
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityOtpBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.activity_login_user_id_et
import kotlinx.android.synthetic.main.activity_otp.*
import javax.inject.Inject

class OTPActivity : BaseActivity<ActivityOtpBinding, OTPViewModel>(), IOTPNavigator {


    @Inject
    override lateinit var viewModel: OTPViewModel

    private var mActivityOtpBinding: ActivityOtpBinding? = null

    @Inject
    lateinit var mOTPViewModel: OTPViewModel



    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_otp



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityOtpBinding = viewDataBinding
        viewModel.navigator = this
        setup()
        activity_otp_signup_btn.setOnClickListener {
            val intent = HomeActivity.newIntent(this@OTPActivity)
            startActivity(intent)
        }
    }


    fun setup() {

        activity_otp_password_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.toString().equals("")){
                    activity_otp_password_et.setBackgroundResource(R.drawable.input_field_white_bg)
                }else{
                    activity_otp_password_et.setBackgroundResource(R.drawable.input_filed_bg)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        activity_otp_re_password_et.addTextChangedListener(object :
                TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.toString().equals("")){
                    activity_otp_re_password_et.setBackgroundResource(R.drawable.input_field_white_bg)
                    activity_otp_signup_btn.isGone = false
                    activity_otp_signup_btn_hide.isGone = true
                }else{
                    activity_otp_re_password_et.setBackgroundResource(R.drawable.input_filed_bg)
                    activity_otp_signup_btn_hide.isGone = false
                    activity_otp_signup_btn.isGone = true
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, OTPActivity::class.java)
        }
    }
}