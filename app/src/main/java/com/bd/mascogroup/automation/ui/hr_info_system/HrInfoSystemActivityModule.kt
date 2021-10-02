package com.bd.mascogroup.automation.ui.hr_info_system

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HrInfoSystemActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( HrInfoSystemViewModel::class)
    abstract fun bindHrInfoSystemViewModel(viewModel: HrInfoSystemViewModel): ViewModel
}