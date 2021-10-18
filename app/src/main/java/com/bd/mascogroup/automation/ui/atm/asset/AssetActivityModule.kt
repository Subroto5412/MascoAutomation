package com.bd.mascogroup.automation.ui.atm.asset

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AssetActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( AssetViewModel::class)
    abstract fun bindAssetViewModel(viewModel: AssetViewModel): ViewModel
}