package com.bd.mascogroup.automation.ui.hr_info.leave_details

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.di.ViewModelKey
import com.bd.mascogroup.automation.ui.hr_info.daily_attendance.DailyAttendanceActivity
import com.bd.mascogroup.automation.ui.hr_info.daily_attendance.DailyAttendanceAdapter
import com.bd.mascogroup.automation.ui.hr_info.daily_attendance.DailyAttendanceStatusAdapter
import com.bd.mascogroup.automation.ui.hr_info.daily_attendance.DailyAttendanceViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import java.util.ArrayList

@Module
abstract class LeaveDetailsActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(LeaveDetailsViewModel::class)
    abstract fun bindLeaveDetailsViewModel(viewModel: LeaveDetailsViewModel): ViewModel
}

@Module
class LeaveDetailsLeaveSummaryAdapterModule {
    @Provides
    fun provideLeaveDetailsViewModel(dataManager: IDataManager, ISchedulerProvider: ISchedulerProvider): LeaveDetailsViewModel {
        return LeaveDetailsViewModel(dataManager, ISchedulerProvider)
    }

    @Provides
    fun provideLeaveDetailsLeaveSummaryAdapter(activity: LeaveDetailsActivity, dataManager: IDataManager): LeaveDetailsLeaveSummaryAdapter {
        return LeaveDetailsLeaveSummaryAdapter(activity, ArrayList(),dataManager)
    }

    @Provides
    fun provideLeaveDetailsAvailSummaryAdapter(activity: LeaveDetailsActivity, dataManager: IDataManager): LeaveDetailsAvailSummaryAdapter {
        return LeaveDetailsAvailSummaryAdapter(activity, ArrayList(),dataManager)
    }
}
