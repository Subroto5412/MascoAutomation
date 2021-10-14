package com.bd.mascogroup.automation.ui.sem.communication_portal

import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import javax.inject.Inject

class CommunicationPortalViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ICommunicationPortalNavigator>(dataManager, ISchedulerProvider) {

}