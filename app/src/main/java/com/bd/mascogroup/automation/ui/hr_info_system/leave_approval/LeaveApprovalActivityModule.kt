package com.bd.mascogroup.automation.ui.hr_info_system.leave_approval

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LeaveApprovalActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( LeaveApprovalViewModel::class)
    abstract fun bindLeaveApprovalViewModel(viewModel: LeaveApprovalViewModel): ViewModel
}