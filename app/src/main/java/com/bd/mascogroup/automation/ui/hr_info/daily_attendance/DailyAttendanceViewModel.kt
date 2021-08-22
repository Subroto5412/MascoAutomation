package com.bd.mascogroup.automation.ui.hr_info.daily_attendance

import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import javax.inject.Inject

class DailyAttendanceViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IDailyAttendanceNavigator>(dataManager, ISchedulerProvider) {

}