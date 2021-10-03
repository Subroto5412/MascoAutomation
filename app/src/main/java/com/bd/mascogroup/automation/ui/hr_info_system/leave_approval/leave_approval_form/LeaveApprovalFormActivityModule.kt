package com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.leave_approval_form

import androidx.lifecycle.ViewModel
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.di.ViewModelKey
import com.bd.mascogroup.automation.ui.hr_info.tax.income_tax.IncomeTaxDeductionActivity
import com.bd.mascogroup.automation.ui.hr_info.tax.income_tax.IncomeTaxDeductionAdapter
import com.bd.mascogroup.automation.ui.hr_info.tax.income_tax.IncomeTaxDeductionViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import java.util.ArrayList

@Module
abstract class LeaveApprovalFormActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey( LeaveApprovalFormViewModel::class)
    abstract fun bindLeaveApprovalFormViewModel(viewModel: LeaveApprovalFormViewModel): ViewModel
}

@Module
class LeaveApprovalFormAdapterModule {
    @Provides
    fun provideLeaveApprovalFormViewModel(dataManager: IDataManager, ISchedulerProvider: ISchedulerProvider): LeaveApprovalFormViewModel {
        return LeaveApprovalFormViewModel(dataManager, ISchedulerProvider)
    }

    @Provides
    fun provideLeaveApprovalFormAdapter(activity: LeaveApprovalFormActivity, dataManager: IDataManager): LeaveApprovalFormAdapter {
        return LeaveApprovalFormAdapter(activity, ArrayList(),dataManager)
    }

}