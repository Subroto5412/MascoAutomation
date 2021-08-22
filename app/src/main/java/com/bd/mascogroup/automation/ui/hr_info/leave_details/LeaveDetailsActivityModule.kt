package com.bd.mascogroup.automation.ui.hr_info.leave_details

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LeaveDetailsActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(LeaveDetailsViewModel::class)
    abstract fun bindLeaveDetailsViewModel(viewModel: LeaveDetailsViewModel): ViewModel
}