package com.bd.mascogroup.automation.ui.hr_info.leave.apply

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LeaveApplyModule {
    @Binds
    @IntoMap
    @ViewModelKey( LeaveApplyViewModel::class)
    abstract fun bindLeaveApplyViewModel(viewModel: LeaveApplyViewModel): ViewModel
}