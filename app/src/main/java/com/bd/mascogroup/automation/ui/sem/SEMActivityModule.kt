package com.bd.mascogroup.automation.ui.sem

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SEMActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( SEMViewModel::class)
    abstract fun bindSEMViewModel(viewModel: SEMViewModel): ViewModel
}