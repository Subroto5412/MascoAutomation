package com.bd.mascogroup.automation.ui.hr_info.tax

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TaxModule {
    @Binds
    @IntoMap
    @ViewModelKey( TaxViewModel::class)
    abstract fun bindTaxViewModel(viewModel: TaxViewModel): ViewModel
}