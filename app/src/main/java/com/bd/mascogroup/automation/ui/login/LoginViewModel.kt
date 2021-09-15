package com.bd.mascogroup.automation.ui.login

import android.content.Context
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.core.view.isGone
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.LoginByUserIdRequest
import com.bd.mascogroup.automation.data.remote.domainModel.LoginRequest
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ILoginNavigator>(dataManager, ISchedulerProvider) {

    fun setup(context: Context, activity_login_user_id_et: EditText, activity_login_password_et: EditText, activity_login_logo_im: ImageView, activity_login_user_im: ImageView, activity_login_user_cl: ConstraintLayout,
              activity_login_user_name_tv: TextView, activity_login_unit_name_tv: TextView, activity_login_signin_btn: MaterialCardView, activity_login_signin_btn_hide: MaterialCardView) {

        activity_login_user_id_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.toString().equals("")) {
                    activity_login_user_id_et.setBackgroundResource(R.drawable.input_field_white_bg)
                    activity_login_logo_im.isGone = true
                    activity_login_user_cl.isGone = false
                    activity_login_user_name_tv.isGone = false
                    activity_login_unit_name_tv.isGone = false

                    if (s.toString().length > 0) {
                        Handler().postDelayed({
                            doSetImage(context, s.toString(), activity_login_user_im,activity_login_user_name_tv,activity_login_unit_name_tv)
                        }, 300)

                    }

                } else {
                    activity_login_user_id_et.setBackgroundResource(R.drawable.input_filed_bg)
                    activity_login_logo_im.isGone = false
                    activity_login_user_cl.isGone = true
                    activity_login_user_name_tv.isGone = true
                    activity_login_unit_name_tv.isGone = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        activity_login_password_et.addTextChangedListener(object :
                TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.toString().equals("")) {
                    activity_login_password_et.setBackgroundResource(R.drawable.input_field_white_bg)
                    activity_login_signin_btn.isGone = false
                    activity_login_signin_btn_hide.isGone = true
                } else {
                    activity_login_password_et.setBackgroundResource(R.drawable.input_filed_bg)
                    activity_login_signin_btn_hide.isGone = false
                    activity_login_signin_btn.isGone = true
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        activity_login_signin_btn.setOnClickListener {

            doLogin(context, activity_login_user_id_et.text.toString(), activity_login_password_et.text.toString())
        }
    }

    fun doLogin(context: Context, empId: String, password: String){
        if(UtilMethods.isConnectedToInternet(context)){
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().doLogin(LoginRequest(empId, password))

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
                        navigator?.openHomeActivity()
                    }else{
                        navigator?.ToastMSG()
                    }
                    UtilMethods.hideLoading()
                }, { error ->
                    UtilMethods.hideLoading()
                    // UtilMethods.showLongToast(context, error.message.toString())
                }
                )
        }else{
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }


    fun doSetImage(context: Context, empCode: String, activity_login_user_im: ImageView,activity_login_user_name_tv:TextView, activity_login_unit_name_tv: TextView){
        if(UtilMethods.isConnectedToInternet(context)){
//            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getLoginImage(empCode)

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ loginByUserIdResponse ->

                        val parts = loginByUserIdResponse.serverFileName.split("\\")
                        val image = parts[1]

                        val url = "https://mis-api.mascoknit.com/EmpImages/"+image
                        Glide.with(activity_login_user_im)
                                .load(url)
                                .into(activity_login_user_im)
                        activity_login_user_name_tv.setText(loginByUserIdResponse.emP_ENAME)
                        activity_login_unit_name_tv.setText(loginByUserIdResponse.unitEName)

//                        UtilMethods.hideLoading()
                    }, { error ->
//                        UtilMethods.hideLoading()
                        // UtilMethods.showLongToast(context, error.message.toString())
                    }
                    )
        }else{
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun isUserIdAndPasswordValid(userId: String, password: String): Boolean {
        // validate email and password
        if (TextUtils.isEmpty(userId)) {
            return false
        }
        return !TextUtils.isEmpty(password)
    }

    fun onServerLoginClick() {
        navigator?.login()
    }


}