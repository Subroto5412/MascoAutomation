package com.bd.mascogroup.automation.ui.hr_info.daily_attendance

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.di.ViewModelKey
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import java.util.ArrayList

@Module
abstract class DailyAttendanceActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(DailyAttendanceViewModel::class)
    abstract fun bindDailyAttendanceViewModel(viewModel: DailyAttendanceViewModel): ViewModel
}

@Module
class DailyAttendanceAdapterModule {
    @Provides
    fun provideDailyAttendanceViewModel(dataManager: IDataManager, ISchedulerProvider: ISchedulerProvider): DailyAttendanceViewModel {
        return DailyAttendanceViewModel(dataManager, ISchedulerProvider)
    }

    @Provides
    fun provideDailyAttendanceAdapter(activity: DailyAttendanceActivity, dataManager: IDataManager): DailyAttendanceAdapter {
        return DailyAttendanceAdapter(activity, ArrayList(),dataManager)
    }

    @Provides
    fun provideDailyAttendanceStatusAdapter(activity: DailyAttendanceActivity, dataManager: IDataManager): DailyAttendanceStatusAdapter {
        return DailyAttendanceStatusAdapter(activity, ArrayList(),dataManager)
    }
}