package com.bd.mascogroup.automation.di.module

import androidx.lifecycle.ViewModelProvider
import com.bd.mascogroup.automation.di.factory.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}