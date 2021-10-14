package com.bd.mascogroup.automation.ui.sem.communication_portal

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CommunicationPortalActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( CommunicationPortalViewModel::class)
    abstract fun bindCommunicationPortalViewModel(viewModel: CommunicationPortalViewModel): ViewModel
}