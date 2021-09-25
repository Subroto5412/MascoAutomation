package com.bd.mascogroup.automation.ui.hr_info.attendance

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AttendanceModule {
    @Binds
    @IntoMap
    @ViewModelKey( AttendanceViewModel::class)
    abstract fun bindAttendanceViewModel(viewModel: AttendanceViewModel): ViewModel
}