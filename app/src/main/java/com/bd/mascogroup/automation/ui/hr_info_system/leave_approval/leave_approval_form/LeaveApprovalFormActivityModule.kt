package com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.leave_approval_form

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LeaveApprovalFormActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( LeaveApprovalFormViewModel::class)
    abstract fun bindLeaveApprovalFormViewModel(viewModel: LeaveApprovalFormViewModel): ViewModel
}