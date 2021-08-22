package com.bd.mascogroup.automation.ui.hr_info

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HRInfoActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( HRInfoViewModel::class)
    abstract fun bindHRInfoViewModel(viewModel: HRInfoViewModel): ViewModel
}