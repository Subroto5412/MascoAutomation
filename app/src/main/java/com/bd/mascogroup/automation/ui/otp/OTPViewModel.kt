package com.bd.mascogroup.automation.ui.otp

import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.ui.signup.ISignupNavigator
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import javax.inject.Inject

class OTPViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IOTPNavigator>(dataManager, ISchedulerProvider) {

}