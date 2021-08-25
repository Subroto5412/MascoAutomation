package com.bd.mascogroup.automation.ui.hr_info.leave_details

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceCardData
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceStatusCardData
import com.bd.mascogroup.automation.data.model.domainModel.LeaveSummaryCardData
import com.bd.mascogroup.automation.data.remote.domainModel.DailyAttendanceStatusResponse
import com.bd.mascogroup.automation.data.remote.domainModel.LeaveSummaryResponse
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import java.util.ArrayList
import javax.inject.Inject

class LeaveDetailsViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ILeaveDetailsNavigator>(dataManager, ISchedulerProvider) {

    var leaveSummaryObserverArrayList: ObservableList<LeaveSummaryCardData> = ObservableArrayList()
    var leaveSummaryListLiveData: MutableLiveData<List<LeaveSummaryCardData>> = MutableLiveData()
    private var leaveSummaryListItems = ArrayList<LeaveSummaryCardData>()

    fun leaveSummary(context: Context) {
        val leaveSummaryResponse1 = LeaveSummaryResponse()
        leaveSummaryResponse1.type_name = "Total Balance"
        leaveSummaryResponse1.cl = "10"
        leaveSummaryResponse1.sl = "14"
        leaveSummaryResponse1.el = "17"

        val leaveSummaryResponse2 = LeaveSummaryResponse()
        leaveSummaryResponse2.type_name = "Avail Day"
        leaveSummaryResponse2.cl = "1"
        leaveSummaryResponse2.sl = "0"
        leaveSummaryResponse2.el = "0"

        val leaveSummaryResponse3 = LeaveSummaryResponse()
        leaveSummaryResponse3.type_name = "Rest Balance"
        leaveSummaryResponse3.cl = "9"
        leaveSummaryResponse3.sl = "14"
        leaveSummaryResponse3.el = "17"

        val leaveSummaryResponse = LeaveSummaryResponse()
        leaveSummaryResponse.type_name = "Leave Type"
        leaveSummaryResponse.cl = "CL"
        leaveSummaryResponse.sl = "SL"
        leaveSummaryResponse.el = "EL"

        leaveSummaryListItems.add(LeaveSummaryCardData(leaveSummaryResponse))
        leaveSummaryListItems.add(LeaveSummaryCardData(leaveSummaryResponse1))
        leaveSummaryListItems.add(LeaveSummaryCardData(leaveSummaryResponse2))
        leaveSummaryListItems.add(LeaveSummaryCardData(leaveSummaryResponse3))


        leaveSummaryListLiveData.value = leaveSummaryListItems
    }


    fun getleaveSummaryLiveData(): MutableLiveData<List<LeaveSummaryCardData>> {
        return leaveSummaryListLiveData
    }

    fun addLeaveSummaryItemToList(Service: List<LeaveSummaryCardData>) {
        leaveSummaryObserverArrayList.clear()
        leaveSummaryObserverArrayList.addAll(Service)
    }

}