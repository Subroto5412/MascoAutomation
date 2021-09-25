package com.bd.mascogroup.automation.ui.hr_info.leave

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LeaveModule {
    @Binds
    @IntoMap
    @ViewModelKey( LeaveViewModel::class)
    abstract fun bindLeaveViewModel(viewModel: LeaveViewModel): ViewModel
}