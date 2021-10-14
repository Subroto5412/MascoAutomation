package com.bd.mascogroup.automation.ui.atm

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ATMActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( ATMViewModel::class)
    abstract fun bindATMViewModel(viewModel: ATMViewModel): ViewModel
}