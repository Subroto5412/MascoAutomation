package com.bd.mascogroup.automation.ui.hr_info.leave.apply

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.FinancialYearCardData
import com.bd.mascogroup.automation.data.model.domainModel.LeavelAvailListCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.LeavelAvailListResponse
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class LeaveApplyViewModel @Inject constructor(
    dataManager: IDataManager,
    ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ILeaveApplyNavigator>(dataManager, ISchedulerProvider){
    private var leavelAvailListCardData = ArrayList<LeavelAvailListCardData>()
    private var LeavelName = ArrayList<String>()

    fun getLeaveList(
        context: Context,
        LeavelNameSp: Spinner,
        activity_leave_apply_designation_value:TextView
    ) {
        leavelAvailListCardData.clear()

        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCallToken().getLeaveList()

            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ leaveResponse ->

                    var lalr = LeavelAvailListResponse()
                    lalr.leaveTypeNo = 0
                    lalr.abbreviation = "Select Leave"
                    lalr.maxBalance = 0
                    lalr.avail = 0
                    leavelAvailListCardData.add(LeavelAvailListCardData(lalr))

                    activity_leave_apply_designation_value.setText(leaveResponse.emp_details.desigEName)

                    leaveResponse._leaveAvailList.forEach {
                        leavelAvailListCardData.add(LeavelAvailListCardData(it))
                    }

                    LeavelName.clear()
                    for (i in 0 until leavelAvailListCardData.size) {
                        val leaveList = HashMap<String, String>()
                        leaveList.put("leaveTypeNo", leavelAvailListCardData.get(i).leaveTypeNo.toString())
                        leaveList.put("abbreviation", "" + leavelAvailListCardData.get(i).abbreviation)
                        leaveList.put("maxBalance", leavelAvailListCardData.get(i).maxBalance.toString())
                        leaveList.put("avail", leavelAvailListCardData.get(i).avail.toString())

                        AppConstants.HasLeaveList.add(leaveList)
                        LeavelName.add(leavelAvailListCardData.get(i).abbreviation)
                    }
                    val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                        context,
                        R.layout.simple_spinner_item,
                        LeavelName
                    )
                    spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item) // The drop down view
                    LeavelNameSp.setAdapter(spinnerArrayAdapter)

                    UtilMethods.hideLoading()
                }, { error ->
                    UtilMethods.hideLoading()
                    //  UtilMethods.showLongToast(context, error.message.toString())
                }
                )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun dataSetting(activity_leave_apply_id_value:TextView, activity_leave_apply_name_value:TextView,activity_leave_apply_designation_value:TextView){
        activity_leave_apply_id_value.setText(dataManager.empCode)
        activity_leave_apply_name_value.setText(dataManager.customerName)
        activity_leave_apply_designation_value.setText("")
    }
}