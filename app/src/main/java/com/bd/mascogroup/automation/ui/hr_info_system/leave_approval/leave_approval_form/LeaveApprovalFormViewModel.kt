package com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.leave_approval_form

import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import javax.inject.Inject

class LeaveApprovalFormViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ILeaveApprovalFormNavigator>(dataManager, ISchedulerProvider) {

}