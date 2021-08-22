package com.bd.mascogroup.automation.ui.splash

import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import javax.inject.Inject

class SplashViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ISplashNavigator>(dataManager, ISchedulerProvider){

    fun startSeeding() {
        navigator!!.openMainActivity()
    }
}