package com.bd.mascogroup.automation.ui.otp

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.OtpRequest
import com.bd.mascogroup.automation.data.remote.domainModel.RegisterRequest
import com.bd.mascogroup.automation.data.remote.domainModel.VerifyOTPRequest
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import com.google.android.material.card.MaterialCardView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_otp.*
import javax.inject.Inject

class OTPViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IOTPNavigator>(dataManager, ISchedulerProvider) {
        var otp:String = ""
        fun doVerifyOTP(context: Context, otp_one_ed:EditText, otp_two_ed:EditText, otp_three_ed:EditText, otp_four_ed:EditText,activity_otp_verified_im:ImageView) {

                otp_one_ed.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                                if (!s.toString().equals("")) {
                                        otp = otp_one_ed.text.toString()+otp_two_ed.text.toString()+otp_three_ed.text.toString()+otp_four_ed.text.toString()
                                        verfiedOTP(context, otp,activity_otp_verified_im)
                                }
                        }

                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        }

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        }
                })


                otp_two_ed.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                                if (!s.toString().equals("")) {
                                        otp = otp_one_ed.text.toString()+otp_two_ed.text.toString()+otp_three_ed.text.toString()+otp_four_ed.text.toString()
                                        verfiedOTP(context, otp,activity_otp_verified_im)
                                }
                        }

                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        }

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        }
                })

                otp_three_ed.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                                if (!s.toString().equals("")) {
                                        otp = otp_one_ed.text.toString()+otp_two_ed.text.toString()+otp_three_ed.text.toString()+otp_four_ed.text.toString()
                                        verfiedOTP(context, otp,activity_otp_verified_im)
                                }
                        }

                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        }

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        }
                })

                otp_four_ed.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                                if (!s.toString().equals("")) {
                                        otp = otp_one_ed.text.toString()+otp_two_ed.text.toString()+otp_three_ed.text.toString()+otp_four_ed.text.toString()
                                        verfiedOTP(context, otp,activity_otp_verified_im)
                                }
                        }

                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        }

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        }
                })

        }

        fun verfiedOTP(context:Context, otp:String,activity_otp_verified_im:ImageView){
                if (UtilMethods.isConnectedToInternet(context)) {
                        UtilMethods.showLoading(context)
                        val observable = ApiServiceCalling.generalMisApiCall().doVerifyOTP(
                                VerifyOTPRequest(otp,AppConstants.EMP_CODE)
                        )

                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ otpResponse ->
                                        if (otpResponse.response.equals("true")){
                                                activity_otp_verified_im.isGone = false
                                        }
                                        UtilMethods.hideLoading()
                                }, { error ->
                                        UtilMethods.hideLoading()
                                        // UtilMethods.showLongToast(context, error.message.toString())
                                }
                                )
                } else {
                        UtilMethods.showLongToast(context, "No Internet Connection!")
                }
        }


        fun setup(context: Context, activity_otp_password_et:EditText, activity_otp_re_password_et:EditText, activity_password_verified_im:ImageView, activity_otp_signup_btn: MaterialCardView, activity_otp_signup_btn_hide:MaterialCardView) {

                activity_otp_password_et.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                                if (!s.toString().equals("")) {
                                        activity_otp_password_et.setBackgroundResource(R.drawable.input_field_white_bg)

                                        if(activity_otp_re_password_et.text.toString().length==s.toString().length){
                                                activity_password_verified_im.isGone = false
                                        }else{
                                                activity_password_verified_im.isGone = true
                                        }

                                } else {
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
                                if (!s.toString().equals("")) {
                                        activity_otp_re_password_et.setBackgroundResource(R.drawable.input_field_white_bg)
                                        activity_otp_signup_btn.isGone = false
                                        activity_otp_signup_btn_hide.isGone = true

                                        if (activity_otp_password_et.text.toString().length!=4){
                                                Toast.makeText(context, "Enter Four digits Password!",Toast.LENGTH_LONG).show()
                                        } else if(activity_otp_password_et.text.toString().length==s.toString().length){
                                                activity_password_verified_im.isGone = false
                                        }else{
                                                activity_password_verified_im.isGone = true
                                        }

                                } else {
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

                activity_otp_signup_btn.setOnClickListener {
                        register(context,activity_otp_re_password_et.text.toString())
                }
        }


        fun register(context:Context,password:String){
                if (UtilMethods.isConnectedToInternet(context)) {
                        UtilMethods.showLoading(context)
                        val observable = ApiServiceCalling.generalMisApiCall().doRegister(RegisterRequest(AppConstants.EMP_CODE, password))

                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ registerResponse ->
                                        if (registerResponse.response.equals("true")){
                                                navigator?.openHomeScreen()
                                        }
                                        UtilMethods.hideLoading()
                                }, { error ->
                                        UtilMethods.hideLoading()
                                        // UtilMethods.showLongToast(context, error.message.toString())
                                }
                                )
                } else {
                        UtilMethods.showLongToast(context, "No Internet Connection!")
                }
        }
}