package com.bd.mascogroup.automation.ui.gpms.bwpd

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class BWPDActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( BWPDViewModel::class)
    abstract fun bindBWPDViewModel(viewModel: BWPDViewModel): ViewModel
}