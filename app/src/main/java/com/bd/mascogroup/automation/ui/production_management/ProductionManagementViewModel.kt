package com.bd.mascogroup.automation.ui.production_management

import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import javax.inject.Inject

class ProductionManagementViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IProductionManagementNavigator>(dataManager, ISchedulerProvider) {

}