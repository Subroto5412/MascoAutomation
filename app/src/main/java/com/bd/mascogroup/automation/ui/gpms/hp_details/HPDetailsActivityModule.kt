package com.bd.mascogroup.automation.ui.gpms.hp_details

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.di.ViewModelKey
import com.bd.mascogroup.automation.ui.gpms.bwpd.BWPDViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import java.util.ArrayList

@Module
abstract class HPDetailsActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( BWPDViewModel::class)
    abstract fun bindHPDetailsViewModel(viewModel: HPDetailsViewModel): ViewModel
}

@Module
class HPDetailsAdapterModule {
    @Provides
    fun provideBWPDViewModel(dataManager: IDataManager, ISchedulerProvider: ISchedulerProvider): HPDetailsViewModel {
        return HPDetailsViewModel(dataManager, ISchedulerProvider)
    }

    @Provides
    fun provideHPDetailsAdapter(activity: HPDetailsActivity, dataManager: IDataManager): HPDetailsAdapter {
        return HPDetailsAdapter(activity, ArrayList(),dataManager)
    }
}