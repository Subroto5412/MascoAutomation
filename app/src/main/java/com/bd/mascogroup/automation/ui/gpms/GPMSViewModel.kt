package com.bd.mascogroup.automation.ui.gpms

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import javax.inject.Inject

class GPMSViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IGPMSNavigator>(dataManager, ISchedulerProvider) {

    fun buttonPermission(context: Context, activity_line_wise_production_cl: ConstraintLayout, activity_hourly_production_data_cl: ConstraintLayout,
                         activity_buyer_wise_production_data_cl: ConstraintLayout, activity_hourly_production_details_cl:ConstraintLayout){
        if (!dataManager.lineWiseProduction.equals("line_wise_production")){
            activity_line_wise_production_cl.isGone = true
        }
        else if (!dataManager.hourlyProductionData.equals("hourly_production_data")){
            activity_hourly_production_data_cl.isGone = true
        }
        else if (!dataManager.hourlyProductionDetails.equals("hourly_production_details")){
            activity_hourly_production_details_cl.isGone = true
        }
        else if (!dataManager.buyerWiseProductionData.equals("buyer_wise_production_data")){
            activity_buyer_wise_production_data_cl.isGone = true
        }
    }

}
