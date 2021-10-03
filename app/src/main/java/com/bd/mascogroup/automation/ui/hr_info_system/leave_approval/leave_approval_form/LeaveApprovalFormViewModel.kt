package com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.leave_approval_form

import android.content.Context
import android.widget.TextView
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.IncomeTaxDeductionCardData
import com.bd.mascogroup.automation.data.model.domainModel.LeaveApprovalListCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.IncomeTaxDeductionResponse
import com.bd.mascogroup.automation.data.remote.domainModel.LeaveApprovalList
import com.bd.mascogroup.automation.data.remote.domainModel.LeavePendingListData
import com.bd.mascogroup.automation.data.remote.domainModel.TaxDeductionRequest
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class LeaveApprovalFormViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ILeaveApprovalFormNavigator>(dataManager, ISchedulerProvider) {

    var leaveApprovalListObserverArrayList: ObservableList<LeaveApprovalListCardData> = ObservableArrayList()
    var leaveApprovalListLiveData: MutableLiveData<List<LeaveApprovalListCardData>> = MutableLiveData()
    private var leaveApprovalListItems = ArrayList<LeaveApprovalListCardData>()


    fun getLeaveApproval(context: Context) {

        /*if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCallToken().getTaxDeduct(TaxDeductionRequest(taxYearNo))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ taxResponse ->
                        taxResponse._taxDeductionsList.forEach {

//                            var incomeTaxDeductionResponse = IncomeTaxDeductionResponse()


                            leaveApprovalListItems.add(LeaveApprovalListCardData(it))
                        }
                        leaveApprovalListLiveData.value = leaveApprovalListItems

                        UtilMethods.hideLoading()
                    }, { error ->
                        UtilMethods.hideLoading()
                        if (error.message.toString().contains("401")){

                        }
                    }
                    )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }*/
        var leavePendingListData = LeavePendingListData()
        leavePendingListData.applyNo = 100
        leavePendingListData.emP_CODE = "109827"
        leavePendingListData.emP_ENAME = "Subroto Mohonto"
        leavePendingListData.desigEName = "Asst. Manager"
        leavePendingListData.applyFromDate = "18-09-2021"
        leavePendingListData.applyToDate = "19-09-2021"
        leavePendingListData.applyDays = "1"
        leavePendingListData.leaveType= "Cl"
        leavePendingListData.leaveAvail = 3

        leaveApprovalListItems.add(LeaveApprovalListCardData(leavePendingListData))
        leaveApprovalListLiveData.value = leaveApprovalListItems
    }

    fun getleaveApprovalListLiveData(): MutableLiveData<List<LeaveApprovalListCardData>> {
        return leaveApprovalListLiveData
    }

    fun addLeaveApprovalListItemToList(Service: List<LeaveApprovalListCardData>) {
        leaveApprovalListObserverArrayList.clear()
        leaveApprovalListObserverArrayList.addAll(Service)
    }
}