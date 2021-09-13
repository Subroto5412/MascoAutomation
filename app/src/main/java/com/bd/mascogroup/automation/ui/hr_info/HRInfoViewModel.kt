package com.bd.mascogroup.automation.ui.hr_info

import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
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

    fun buttonPermission(activity_hr_main_daily_attendance_cl:ConstraintLayout, activity_hr_main_leave_details_cl:ConstraintLayout, activity_hr_main_income_tax_cl:ConstraintLayout){
        if (!dataManager.dailyAttendance.equals("daily_attendance")){
            activity_hr_main_daily_attendance_cl.isGone = true
        }
        if (!dataManager.leaveHistory.equals("leave_history")){
            activity_hr_main_leave_details_cl.isGone = true
        }

        if (!dataManager.taxHistory.equals("tax_history")){
            activity_hr_main_income_tax_cl.isGone = true
        }
    }
}