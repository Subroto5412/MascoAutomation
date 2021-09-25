package com.bd.mascogroup.automation.ui.hr_info.leave

import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import javax.inject.Inject

class LeaveViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ILeaveNavigation>(dataManager, ISchedulerProvider){

}