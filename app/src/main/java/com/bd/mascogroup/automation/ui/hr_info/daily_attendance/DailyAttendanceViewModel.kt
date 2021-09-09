package com.bd.mascogroup.automation.ui.hr_info.daily_attendance

import android.R
import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceCardData
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceStatusCardData
import com.bd.mascogroup.automation.data.model.domainModel.FinancialYearCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.DailyAttendanceResponse
import com.bd.mascogroup.automation.data.remote.domainModel.DailyAttendanceStatusResponse
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class DailyAttendanceViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IDailyAttendanceNavigator>(dataManager, ISchedulerProvider) {

    private var financialYearCardData = ArrayList<FinancialYearCardData>()
    private var FinancialYearNames = ArrayList<String>()
    var dailyAttendanceObserverArrayList: ObservableList<DailyAttendanceCardData> = ObservableArrayList()
    var dailyAttendanceListLiveData: MutableLiveData<List<DailyAttendanceCardData>> = MutableLiveData()
    private var dailyAttendanceListItems = ArrayList<DailyAttendanceCardData>()

    var dailyAttendanceStatusObserverArrayList: ObservableList<DailyAttendanceStatusCardData> = ObservableArrayList()
    var dailyAttendanceStatusListLiveData: MutableLiveData<List<DailyAttendanceStatusCardData>> = MutableLiveData()
    private var dailyAttendanceStatusListItems = ArrayList<DailyAttendanceStatusCardData>()


    fun dailyAttendance(context:Context){
      val dailyAttendance2 = DailyAttendanceResponse()
        dailyAttendance2.additionTime = "2.04"
        dailyAttendance2.FPunchIn = "09:15:00"
        dailyAttendance2.FPunchOut = "08:11:00"
        dailyAttendance2.FSts = "A"
        dailyAttendance2.PunchDate= "24"
        dailyAttendance2.ShiftIn= "09:00:00"
        dailyAttendance2.ShiftLate= ""
        dailyAttendance2.ShiftName= "S"
        dailyAttendance2.ShiftOut = "06:00:00"


        val dailyAttendance3 = DailyAttendanceResponse()
        dailyAttendance3.additionTime = "1.10"
        dailyAttendance3.FPunchIn = "09:20:00"
        dailyAttendance3.FPunchOut = "07:10:00"
        dailyAttendance3.FSts = "P"
        dailyAttendance3.PunchDate= "23"
        dailyAttendance3.ShiftIn= "09:10:00"
        dailyAttendance3.ShiftLate= ""
        dailyAttendance3.ShiftName= "S"
        dailyAttendance3.ShiftOut = "06:05:00"


        val dailyAttendance = DailyAttendanceResponse()
        dailyAttendance.additionTime = ""
        dailyAttendance.FPunchIn = "09:00:00"
        dailyAttendance.FPunchOut = "06:05:00"
        dailyAttendance.FSts = "P"
        dailyAttendance.PunchDate= "22"
        dailyAttendance.ShiftIn= "09:10:00"
        dailyAttendance.ShiftLate= ""
        dailyAttendance.ShiftName= "S"
        dailyAttendance.ShiftOut = "06:05:00"

        dailyAttendanceListItems.add(DailyAttendanceCardData(dailyAttendance2))
        dailyAttendanceListItems.add(DailyAttendanceCardData(dailyAttendance3))
        dailyAttendanceListItems.add(DailyAttendanceCardData(dailyAttendance))

        dailyAttendanceListLiveData.value = dailyAttendanceListItems

        dailyAttendanceStatus(context)
    }

    fun dailyAttendanceStatus(context:Context) {
        val dailyAttendanceStatus = DailyAttendanceStatusResponse()
        dailyAttendanceStatus.status = "Late"
        dailyAttendanceStatus.statusValue = "1"


        val dailyAttendanceStatus2 = DailyAttendanceStatusResponse()
        dailyAttendanceStatus2.status = "Present"
        dailyAttendanceStatus2.statusValue = "15"

        val dailyAttendanceStatus3 = DailyAttendanceStatusResponse()
        dailyAttendanceStatus3.status = "HL"
        dailyAttendanceStatus3.statusValue = "2"

        val dailyAttendanceStatus4 = DailyAttendanceStatusResponse()
        dailyAttendanceStatus4.status = "Absent"
        dailyAttendanceStatus4.statusValue = "3"


        val dailyAttendanceStatus5 = DailyAttendanceStatusResponse()
        dailyAttendanceStatus5.status = "Quick Out"
        dailyAttendanceStatus5.statusValue = "5"

        dailyAttendanceStatusListItems.add(DailyAttendanceStatusCardData(dailyAttendanceStatus))
        dailyAttendanceStatusListItems.add(DailyAttendanceStatusCardData(dailyAttendanceStatus3))
        dailyAttendanceStatusListItems.add(DailyAttendanceStatusCardData(dailyAttendanceStatus2))
        dailyAttendanceStatusListItems.add(DailyAttendanceStatusCardData(dailyAttendanceStatus4))
        dailyAttendanceStatusListItems.add(DailyAttendanceStatusCardData(dailyAttendanceStatus5))

        dailyAttendanceStatusListLiveData.value = dailyAttendanceStatusListItems
    }

    fun getdailyAttendanceLiveData(): MutableLiveData<List<DailyAttendanceCardData>> {
        return dailyAttendanceListLiveData
    }

    fun addDailyAttendanceItemToList(Service: List<DailyAttendanceCardData>) {
        dailyAttendanceObserverArrayList.clear()
        dailyAttendanceObserverArrayList.addAll(Service)
    }

    fun getdailyAttendanceStatusLiveData(): MutableLiveData<List<DailyAttendanceStatusCardData>> {
        return dailyAttendanceStatusListLiveData
    }

    fun addDailyAttendanceStatusItemToList(Service: List<DailyAttendanceStatusCardData>) {
        dailyAttendanceStatusObserverArrayList.clear()
        dailyAttendanceStatusObserverArrayList.addAll(Service)
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
                           // fYear.put("finalYearNo", financialYearCardData.get(i).finalYearNo.toString())
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