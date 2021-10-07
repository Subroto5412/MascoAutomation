package com.bd.mascogroup.automation.ui.gpms.lwp

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.di.ViewModelKey
import com.bd.mascogroup.automation.ui.gpms.hpd.HPDActivity
import com.bd.mascogroup.automation.ui.gpms.hpd.HPDAdapter
import com.bd.mascogroup.automation.ui.gpms.hpd.HPDViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import java.util.ArrayList

@Module
abstract class LWPActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( LWPViewModel::class)
    abstract fun bindLWPViewModel(viewModel: LWPViewModel): ViewModel
}

@Module
class LWPAdapterModule {
    @Provides
    fun provideLWPViewModel(dataManager: IDataManager, ISchedulerProvider: ISchedulerProvider): LWPViewModel {
        return LWPViewModel(dataManager, ISchedulerProvider)
    }

    @Provides
    fun provideLWPAdapter(activity: LWPActivity, dataManager: IDataManager): LWPAdapter {
        return LWPAdapter(activity, ArrayList(),dataManager)
    }
}