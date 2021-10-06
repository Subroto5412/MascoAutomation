package com.bd.mascogroup.automation.ui.gpms.bwpd

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.di.ViewModelKey
import com.bd.mascogroup.automation.ui.hr_info.attendance.daily_attendance.DailyAttendanceActivity
import com.bd.mascogroup.automation.ui.hr_info.attendance.daily_attendance.DailyAttendanceAdapter
import com.bd.mascogroup.automation.ui.hr_info.attendance.daily_attendance.DailyAttendanceStatusAdapter
import com.bd.mascogroup.automation.ui.hr_info.attendance.daily_attendance.DailyAttendanceViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import java.util.ArrayList

@Module
abstract class BWPDActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( BWPDViewModel::class)
    abstract fun bindBWPDViewModel(viewModel: BWPDViewModel): ViewModel
}

@Module
class BWPDAdapterModule {
    @Provides
    fun provideBWPDViewModel(dataManager: IDataManager, ISchedulerProvider: ISchedulerProvider):BWPDViewModel {
        return BWPDViewModel(dataManager, ISchedulerProvider)
    }

    @Provides
    fun provideBWPDAdapter(activity: BWPDActivity, dataManager: IDataManager): BWPDAdapter {
        return BWPDAdapter(activity, ArrayList(),dataManager)
    }
}