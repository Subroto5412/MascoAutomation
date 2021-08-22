package com.bd.mascogroup.automation.ui.hr_info.leave_details

import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import javax.inject.Inject

class LeaveDetailsViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ILeaveDetailsNavigator>(dataManager, ISchedulerProvider) {

}