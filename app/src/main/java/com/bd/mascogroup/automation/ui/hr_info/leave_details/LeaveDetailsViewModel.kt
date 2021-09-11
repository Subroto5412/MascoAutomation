package com.bd.mascogroup.automation.ui.hr_info.leave_details

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.*
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.AvailSummaryResponse
import com.bd.mascogroup.automation.data.remote.domainModel.DailyAttendanceStatusResponse
import com.bd.mascogroup.automation.data.remote.domainModel.LeaveSummaryResponse
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class LeaveDetailsViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ILeaveDetailsNavigator>(dataManager, ISchedulerProvider) {

    private var financialYearCardData = ArrayList<FinancialYearCardData>()
    private var FinancialYearNames = ArrayList<String>()

    var leaveSummaryObserverArrayList: ObservableList<LeaveSummaryCardData> = ObservableArrayList()
    var leaveSummaryListLiveData: MutableLiveData<List<LeaveSummaryCardData>> = MutableLiveData()
    private var leaveSummaryListItems = ArrayList<LeaveSummaryCardData>()

    var availSummaryObserverArrayList: ObservableList<AvailSummaryCardData> = ObservableArrayList()
    var availSummaryListLiveData: MutableLiveData<List<AvailSummaryCardData>> = MutableLiveData()
    private var availSummaryListItems = ArrayList<AvailSummaryCardData>()

    fun leaveSummary(context: Context, fYEarSpId:Int) {
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
        availSummary(context)
    }

    fun availSummary(context: Context){

        val availSummaryResponse5 = AvailSummaryResponse()
        availSummaryResponse5.sl = "1"
        availSummaryResponse5.leaveType = "CL"
        availSummaryResponse5.availDay = "1"
        availSummaryResponse5.fromDate = "12-06-2021"
        availSummaryResponse5.toDate = "12-06-2021"
        availSummaryResponse5.applicationDate = "15-06-2021"

        val availSummaryResponse4 = AvailSummaryResponse()
        availSummaryResponse4.sl = "1"
        availSummaryResponse4.leaveType = "CL"
        availSummaryResponse4.availDay = "1"
        availSummaryResponse4.fromDate = "12-06-2021"
        availSummaryResponse4.toDate = "12-06-2021"
        availSummaryResponse4.applicationDate = "15-06-2021"

        val availSummaryResponse3 = AvailSummaryResponse()
        availSummaryResponse3.sl = "1"
        availSummaryResponse3.leaveType = "SL"
        availSummaryResponse3.availDay = "1"
        availSummaryResponse3.fromDate = "12-06-2021"
        availSummaryResponse3.toDate = "12-06-2021"
        availSummaryResponse3.applicationDate = "15-06-2021"


        val availSummaryResponse2 = AvailSummaryResponse()
        availSummaryResponse2.sl = "1"
        availSummaryResponse2.leaveType = "CL"
        availSummaryResponse2.availDay = "1"
        availSummaryResponse2.fromDate = "12-06-2021"
        availSummaryResponse2.toDate = "12-06-2021"
        availSummaryResponse2.applicationDate = "15-06-2021"


        val availSummaryResponse1 = AvailSummaryResponse()
        availSummaryResponse1.sl = "1"
        availSummaryResponse1.leaveType = "EL"
        availSummaryResponse1.availDay = "1"
        availSummaryResponse1.fromDate = "12-06-2021"
        availSummaryResponse1.toDate = "12-06-2021"
        availSummaryResponse1.applicationDate = "15-06-2021"


        val availSummaryResponse = AvailSummaryResponse()
        availSummaryResponse.sl = "1"
        availSummaryResponse.leaveType = "EL"
        availSummaryResponse.availDay = "1"
        availSummaryResponse.fromDate = "12-06-2021"
        availSummaryResponse.toDate = "12-06-2021"
        availSummaryResponse.applicationDate = "15-06-2021"

        availSummaryListItems.add(AvailSummaryCardData(availSummaryResponse))
        availSummaryListItems.add(AvailSummaryCardData(availSummaryResponse1))
        availSummaryListItems.add(AvailSummaryCardData(availSummaryResponse2))
        availSummaryListItems.add(AvailSummaryCardData(availSummaryResponse3))
        availSummaryListItems.add(AvailSummaryCardData(availSummaryResponse4))
        availSummaryListItems.add(AvailSummaryCardData(availSummaryResponse5))
        availSummaryListLiveData.value = availSummaryListItems
    }

    fun getleaveSummaryLiveData(): MutableLiveData<List<LeaveSummaryCardData>> {
        return leaveSummaryListLiveData
    }

    fun addLeaveSummaryItemToList(Service: List<LeaveSummaryCardData>) {
        leaveSummaryObserverArrayList.clear()
        leaveSummaryObserverArrayList.addAll(Service)
    }

    fun getavailSummaryLiveData(): MutableLiveData<List<AvailSummaryCardData>> {
        return availSummaryListLiveData
    }

    fun addAvailSummaryItemToList(Service: List<AvailSummaryCardData>) {
        availSummaryObserverArrayList.clear()
        availSummaryObserverArrayList.addAll(Service)
    }


    fun getFinancialYear(
            context: Context,
            fYearSpinner: Spinner
    ) {
        financialYearCardData.clear()
        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getFinancialYear()

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ yearResponse ->

                        yearResponse._listFinalYear.forEach {
                            financialYearCardData.add(FinancialYearCardData(it))
                        }
                        FinancialYearNames.clear()
                        for (i in 0 until financialYearCardData.size) {
                            val fYear = HashMap<String, String>()
                            fYear.put("finalYearNo", financialYearCardData.get(i).finalYearNo.toString())
                            fYear.put("finalYearName", "" + financialYearCardData.get(i).finalYearName)
                            fYear.put("yearName", financialYearCardData.get(i).yearName)

                            AppConstants.HasYearList.add(fYear)
                            FinancialYearNames.add(financialYearCardData.get(i).finalYearName)
                        }
                        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                                context,
                                R.layout.simple_spinner_item,
                                FinancialYearNames
                        )
                        spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item) // The drop down view

                        fYearSpinner.setAdapter(spinnerArrayAdapter)


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
}