package com.bd.mascogroup.automation.ui.gpms

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GPMSActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( GPMSViewModel::class)
    abstract fun bindGPMSViewModel(viewModel: GPMSViewModel): ViewModel
}