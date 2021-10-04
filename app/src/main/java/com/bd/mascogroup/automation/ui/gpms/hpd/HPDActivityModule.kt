package com.bd.mascogroup.automation.ui.gpms.hpd

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HPDActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( HPDViewModel::class)
    abstract fun bindHPDViewModel(viewModel: HPDViewModel): ViewModel
}