package com.bd.mascogroup.automation.ui.production_management

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProductionManagementActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProductionManagementViewModel::class)
    abstract fun bindProductionManagementViewModel(viewModel: ProductionManagementViewModel): ViewModel
}