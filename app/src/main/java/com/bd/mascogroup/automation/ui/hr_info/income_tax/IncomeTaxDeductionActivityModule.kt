package com.bd.mascogroup.automation.ui.hr_info.income_tax

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.di.ViewModelKey
import com.bd.mascogroup.automation.ui.hr_info.leave_details.LeaveDetailsActivity
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import java.util.ArrayList

@Module
abstract class IncomeTaxDeductionActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(IncomeTaxDeductionViewModel::class)
    abstract fun bindIncomeTaxDeductionViewModel(viewModel: IncomeTaxDeductionViewModel): ViewModel
}

@Module
class IncomeTaxDeductionAdapterModule {
    @Provides
    fun provideIncomeTaxDeductionViewModel(dataManager: IDataManager, ISchedulerProvider: ISchedulerProvider): IncomeTaxDeductionViewModel {
        return IncomeTaxDeductionViewModel(dataManager, ISchedulerProvider)
    }

    @Provides
    fun provideIncomeTaxDeductionAdapter(activity: IncomeTaxDeductionActivity, dataManager: IDataManager): IncomeTaxDeductionAdapter {
        return IncomeTaxDeductionAdapter(activity, ArrayList(),dataManager)
    }

}
