package com.bd.mascogroup.automation.ui.gpms.lwp

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LWPActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( LWPViewModel::class)
    abstract fun bindLWPViewModel(viewModel: LWPViewModel): ViewModel
}