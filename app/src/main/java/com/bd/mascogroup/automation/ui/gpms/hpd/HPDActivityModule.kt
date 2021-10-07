package com.bd.mascogroup.automation.ui.gpms.hpd

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.di.ViewModelKey
import com.bd.mascogroup.automation.ui.gpms.bwpd.BWPDActivity
import com.bd.mascogroup.automation.ui.gpms.bwpd.BWPDAdapter
import com.bd.mascogroup.automation.ui.gpms.bwpd.BWPDViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import java.util.ArrayList

@Module
abstract class HPDActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( HPDViewModel::class)
    abstract fun bindHPDViewModel(viewModel: HPDViewModel): ViewModel
}

@Module
class HPDAdapterModule {
    @Provides
    fun provideHPDViewModel(dataManager: IDataManager, ISchedulerProvider: ISchedulerProvider): HPDViewModel {
        return HPDViewModel(dataManager, ISchedulerProvider)
    }

    @Provides
    fun provideHPDAdapter(activity: HPDActivity, dataManager: IDataManager): HPDAdapter {
        return HPDAdapter(activity, ArrayList(),dataManager)
    }
}