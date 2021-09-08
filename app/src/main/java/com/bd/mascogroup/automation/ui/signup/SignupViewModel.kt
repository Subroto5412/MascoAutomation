package com.bd.mascogroup.automation.ui.signup

import android.content.Context
import android.util.Log
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.OtpRequest
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SignupViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ISignupNavigator>(dataManager, ISchedulerProvider) {

        fun doSendOTP(context: Context, empCode: String) {
                if (UtilMethods.isConnectedToInternet(context)) {
                        UtilMethods.showLoading(context)
                        val observable = ApiServiceCalling.generalMisApiCall().doSendOTP(OtpRequest(empCode))

                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ otpResponse ->
                                        AppConstants.MOBILE_NO = otpResponse.mobile
                                        dataManager.mobile = otpResponse.mobile
                                        navigator?.openOtpActivity()
                                        UtilMethods.hideLoading()
                                }, { error ->
                                    navigator?.openOtpActivity()
                                        UtilMethods.hideLoading()
                                        // UtilMethods.showLongToast(context, error.message.toString())
                                }
                                )
                } else {
                        UtilMethods.showLongToast(context, "No Internet Connection!")
                }
        }

}