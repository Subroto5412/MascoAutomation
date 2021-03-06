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
import com.bd.mascogroup.automation.data.remote.domainModel.*
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import com.bumptech.glide.Glide
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
        fun doVerifyOTP(context: Context, activity_otp_n1_value_text:TextView, otp_one_ed:EditText, otp_two_ed:EditText, otp_three_ed:EditText, otp_four_ed:EditText,activity_otp_verified_im:ImageView) {

                activity_otp_n1_value_text.setText(dataManager.mobile)

                otp_one_ed.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                                if (!s.toString().equals("")) {
                                        otp = otp_one_ed.text.toString()+otp_two_ed.text.toString()+otp_three_ed.text.toString()+otp_four_ed.text.toString()
                                        verfiedOTP(context, otp,activity_otp_verified_im)

                                        if (s.toString().length==1)
                                        otp_two_ed.requestFocus()
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

                                        if (s.toString().length==1)
                                        otp_three_ed.requestFocus()
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

                                        if (s.toString().length==1)
                                        otp_four_ed.requestFocus()
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

                                        if(activity_otp_re_password_et.text.toString().equals(s.toString())){
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

                                        if(activity_otp_password_et.text.toString().equals(s.toString())){
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
                        if (activity_otp_password_et.text.toString().length!=4)
                        {
                                Toast.makeText(context, "Enter Four digits Password!",Toast.LENGTH_LONG).show()
                        }else if (activity_otp_re_password_et.text.toString().length!=4)
                        {
                                Toast.makeText(context, "Enter Four digits Password!",Toast.LENGTH_LONG).show() }
                        else if (!activity_otp_re_password_et.text.toString().equals(activity_otp_password_et.text.toString()))
                        {
                                Toast.makeText(context, "Password isn't matching!",Toast.LENGTH_LONG).show()
                        } else{
                                register(context,activity_otp_re_password_et.text.toString())
                        }

                }
        }


        fun register(context:Context,password:String){
                if (UtilMethods.isConnectedToInternet(context)) {
                        UtilMethods.showLoading(context)
                        val observable = ApiServiceCalling.generalMisApiCall().doRegister(RegisterRequest(AppConstants.EMP_CODE, password))

                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ registerResponse ->
                                        UtilMethods.hideLoading()
                                        if (registerResponse.response == true){
                                                doLogin(context, AppConstants.EMP_CODE, password)
                                        }
                                }, { error ->
                                        UtilMethods.hideLoading()
                                        // UtilMethods.showLongToast(context, error.message.toString())
                                }
                                )
                } else {
                        UtilMethods.showLongToast(context, "No Internet Connection!")
                }
        }

        fun doLogin(context: Context, empCode: String, password: String){
                if(UtilMethods.isConnectedToInternet(context)){
                        UtilMethods.showLoading(context)
                        val observable = ApiServiceCalling.generalMisApiCall().doLogin(LoginRequest(empCode, password))

                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ loginResponse ->
                                        if (loginResponse.empId!=0){
                                                dataManager.mobile = loginResponse.mobile
                                                dataManager.empId = loginResponse.empId.toString()
                                                dataManager.empCode = loginResponse.empCode
                                                dataManager.accessToken = loginResponse.token
                                                dataManager.refreshToken = loginResponse.refresh_token
                                                AppConstants.acceessToken = loginResponse.token

                                                if (!loginResponse._permissionList.isNullOrEmpty()){
                                                        loginResponse._permissionList.forEach { permissionListResponse->
                                                                if (permissionListResponse.moduleName.equals("HRModule"))
                                                                        dataManager.HRModule = permissionListResponse.moduleName


                                                                permissionListResponse._subMenuList.forEach {
                                                                        if (it.activityName.equals("daily_attendance"))
                                                                                dataManager.dailyAttendance = it.activityName

                                                                        if (it.activityName.equals("leave_history"))
                                                                                dataManager.leaveHistory = it.activityName

                                                                        if (it.activityName.equals("tax_history"))
                                                                                dataManager.taxHistory = it.activityName
                                                                }
                                                        }
                                                }else{
                                                        dataManager.HRModule = ""
                                                        dataManager.dailyAttendance = ""
                                                        dataManager.leaveHistory = ""
                                                        dataManager.taxHistory = ""
                                                }
                                        }
                                        UtilMethods.hideLoading()
                                        doSetImage(context, empCode)
                                }, { error ->
                                        UtilMethods.hideLoading()
                                        // UtilMethods.showLongToast(context, error.message.toString())
                                }
                                )
                }else{
                        UtilMethods.showLongToast(context, "No Internet Connection!")
                }
        }

        fun doSetImage(context: Context, empCode: String) {
                if (UtilMethods.isConnectedToInternet(context)) {
                        UtilMethods.showLoading(context)
                        val observable = ApiServiceCalling.generalMisApiCall().getLoginImage(empCode)

                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ loginByUserIdResponse ->
                                        dataManager.unitName = loginByUserIdResponse.unitEName
                                        dataManager.customerName = loginByUserIdResponse.emP_ENAME

                                        val parts = loginByUserIdResponse.serverFileName.split("\\")
                                        val image = parts[1]
                                        dataManager.customerPic = "https://mis-api.mascoknit.com/EmpImages/" + image

                                        UtilMethods.hideLoading()
                                        sendFCMToken(context)
                                }, { error ->
                                        UtilMethods.hideLoading()
                                        // UtilMethods.showLongToast(context, error.message.toString())
                                }
                                )
                } else {
                        UtilMethods.showLongToast(context, "No Internet Connection!")
                }
        }



        fun doReSendOTP(context: Context) {
                if (UtilMethods.isConnectedToInternet(context)) {
                        UtilMethods.showLoading(context)
                        val observable = ApiServiceCalling.generalMisApiCall().doSendOTP(OtpRequest(dataManager.empCode))

                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ otpResponse ->
                                        if(!otpResponse.mobile.isNullOrEmpty()){

                                                AppConstants.MOBILE_NO = otpResponse.mobile
                                                dataManager.mobile = otpResponse.mobile
                                        }

                                        UtilMethods.hideLoading()
                                        UtilMethods.showLongToast(context, "OTP send Successfully")
                                }, { error ->

                                        UtilMethods.hideLoading()
                                        // UtilMethods.showLongToast(context, error.message.toString())
                                }
                                )
                } else {
                        UtilMethods.showLongToast(context, "No Internet Connection!")
                }
        }

        fun sendFCMToken(context: Context){
                if(UtilMethods.isConnectedToInternet(context)){
                        UtilMethods.showLoading(context)
                        val observable = ApiServiceCalling.generalMisApiCallToken().doFCMToken(TokenRequest(AppConstants.fcmToken))

                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ fcmResponse ->
                                        UtilMethods.hideLoading()
                                        navigator?.openHomeScreen()
                                }, { error ->
                                        UtilMethods.hideLoading()
                                        navigator?.openHomeScreen()
                                        // UtilMethods.showLongToast(context, error.message.toString())
                                }
                                )
                }else{
                        UtilMethods.showLongToast(context, "No Internet Connection!")
                }
        }
}