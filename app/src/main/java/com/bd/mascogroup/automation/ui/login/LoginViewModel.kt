package com.bd.mascogroup.automation.ui.login

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.LoginByUserIdRequest
import com.bd.mascogroup.automation.data.remote.domainModel.LoginRequest
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import com.google.android.material.card.MaterialCardView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Retrofit
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ILoginNavigator>(dataManager, ISchedulerProvider) {

    /*fun login(loginId: String, password: String, context: Context) {

        if(UtilMethods.isConnectedToInternet(context)){
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.loginApiCall().doLogin(LoginPostData(loginId, password))

            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ loginResponse ->
                    if(loginResponse.response=="true")
                    {
                        dataManager.deliveryManId  = loginResponse.data.delivery_man_data_id
                        navigator?.openOrderListActivity()
                    }
                    UtilMethods.hideLoading()
                }, { error ->
                    UtilMethods.hideLoading()
                    UtilMethods.showLongToast(context, error.message.toString())
                }
                )
        }else{
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }*/

    fun setup(context:Context,activity_login_user_id_et: EditText, activity_login_password_et: EditText, activity_login_logo_im: ImageView, activity_login_user_cl: ConstraintLayout,
              activity_login_user_name_tv: TextView, activity_login_unit_name_tv: TextView, activity_login_signin_btn: MaterialCardView, activity_login_signin_btn_hide: MaterialCardView) {

        activity_login_user_id_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.toString().equals("")){
                    activity_login_user_id_et.setBackgroundResource(R.drawable.input_field_white_bg)
                    activity_login_logo_im.isGone = true
                    activity_login_user_cl.isGone = false
                    activity_login_user_name_tv.isGone = false
                    activity_login_unit_name_tv.isGone = false

                    if (s.toString().length>5){
                        if(UtilMethods.isConnectedToInternet(context)){
                            UtilMethods.showLoading(context)
                            val observable = ApiServiceCalling.generalAPPSApiCall().doLoginUserId(LoginByUserIdRequest(s.toString()))

                            observable.subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe({ loginByUserIdResponse ->
                                        Log.e("-----","-------"+loginByUserIdResponse.copy())
                                        UtilMethods.hideLoading()
                                    }, { error ->
                                        Log.e("-----","-----ggg--")
                                        UtilMethods.hideLoading()
                                        // UtilMethods.showLongToast(context, error.message.toString())
                                    }
                                    )
                        }else{
                            UtilMethods.showLongToast(context, "No Internet Connection!")
                        }
                    }

                }else{
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
                if (!s.toString().equals("")){
                    activity_login_password_et.setBackgroundResource(R.drawable.input_field_white_bg)
                    activity_login_signin_btn.isGone = false
                    activity_login_signin_btn_hide.isGone = true
                }else{
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
            doLogin(context, activity_login_user_id_et.text.toString(),  activity_login_password_et.text.toString())
        }

    }

    fun doLogin(context:Context, userName: String, password:String){
        if(UtilMethods.isConnectedToInternet(context)){
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalAPPSApiCall().doLogin(LoginRequest(userName, password))

            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ loginByUserIdResponse ->
                    Log.e("-----","-------"+loginByUserIdResponse.copy())
                    UtilMethods.hideLoading()
                }, { error ->
                    Log.e("-----","-----ggg--")
                    UtilMethods.hideLoading()
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