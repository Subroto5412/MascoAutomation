package com.bd.mascogroup.automation.ui.hr_info.daily_attendance

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DailyAttendanceActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(DailyAttendanceViewModel::class)
    abstract fun bindDailyAttendanceViewModel(viewModel: DailyAttendanceViewModel): ViewModel
}