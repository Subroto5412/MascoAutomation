package com.bd.mascogroup.automation.ui.hr_info.tax

import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import javax.inject.Inject


class TaxViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ITaxNavigation>(dataManager, ISchedulerProvider){

}