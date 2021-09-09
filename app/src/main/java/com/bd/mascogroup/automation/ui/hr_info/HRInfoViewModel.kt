package com.bd.mascogroup.automation.ui.hr_info

import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import javax.inject.Inject

class HRInfoViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IHRInfoNavigator>(dataManager, ISchedulerProvider) {

    fun accessToken(){
        AppConstants.acceessToken = dataManager.accessToken
    }

}