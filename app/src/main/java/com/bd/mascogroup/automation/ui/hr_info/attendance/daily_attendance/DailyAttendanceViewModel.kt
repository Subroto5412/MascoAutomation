package com.bd.mascogroup.automation.ui.hr_info.attendance.daily_attendance

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
import com.bd.mascogroup.automation.data.remote.domainModel.*
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


    fun dailyAttendance(context:Context,fromDate:String,toDate:String){
//        dailyAttendanceStatusListItems.clear()
        dailyAttendanceListItems.clear()

        if(UtilMethods.isConnectedToInternet(context)){
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCallToken().getAllAttendance(DailyAttendanceRequest(fromDate,toDate))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ attendanceResponse ->

                        attendanceResponse._attHistoryListStr.forEach {
                            dailyAttendanceListItems.add(DailyAttendanceCardData(it))
                        }
                        dailyAttendanceListLiveData.value = dailyAttendanceListItems

                        UtilMethods.hideLoading()
//                        dailyAttendanceSummary(context, fromDate, toDate)
                    }, { error ->
                        UtilMethods.hideLoading()
                        if (error.message.toString().contains("401")){
                            getRefreshToken(context)
                        }
//                        UtilMethods.showLongToast(context, error.message.toString())

                    }
                    )
        }else{
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }


    fun dailyAttendanceSummary(context:Context,fromDate:String,toDate:String){
        dailyAttendanceStatusListItems.clear()
//        dailyAttendanceListItems.clear()

        if(UtilMethods.isConnectedToInternet(context)){
         //   UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCallToken().getAttendanceSummary(DailyAttendanceRequest(fromDate,toDate))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ attendanceResponse ->
                        attendanceResponse.allLeaveCount.forEach {
                            Log.e("---------","---------status-------"+it.status)
                            Log.e("---------","---------statusValue-------"+it.statusValue)
                            dailyAttendanceStatusListItems.add(DailyAttendanceStatusCardData(it))
                        }
                        dailyAttendanceStatusListLiveData.value = dailyAttendanceStatusListItems
                      //  UtilMethods.hideLoading()
                    }, { error ->
                       /* UtilMethods.hideLoading()
                        if (error.message.toString().contains("401")){
                            getRefreshToken(context)
                        }*/
//                        UtilMethods.showLongToast(context, error.message.toString())

                    }
                    )
        }else{
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
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


    fun getRefreshToken(context:Context){

        if(UtilMethods.isConnectedToInternet(context)){
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getRefreshToken(RefreshTokenRequest(dataManager.accessToken,dataManager.refreshToken))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ refreshTokenResponse ->
                       if (refreshTokenResponse.error.isNullOrEmpty()){
                           dataManager.accessToken = refreshTokenResponse.rftoken.jwtToken
                           dataManager.refreshToken = refreshTokenResponse.rftoken.refresh_token
                       }
                        UtilMethods.hideLoading()
                        navigator?.openHRScreen()
                    }, { error ->
                        UtilMethods.hideLoading()
                    }
                    )
        }else{
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun monthNameConvert(monthName:String):String{
        var month:String = ""
        if(monthName.equals("January")){
            month = "01"
        }else if(monthName.equals("February")){
            month = "02"
        }
        else if(monthName.equals("March")){
            month = "03"
        }
        else if(monthName.equals("April")){
            month = "04"
        }
        else if(monthName.equals("May")){
            month = "05"
        }
        else if(monthName.equals("June")){
            month = "06"
        }
        else if(monthName.equals("July")){
            month = "07"
        }
        else if(monthName.equals("August")){
            month = "08"
        }
        else if(monthName.equals("September")){
            month = "09"
        }
        else if(monthName.equals("October")){
            month = "10"
        }
        else if(monthName.equals("November")){
            month = "11"
        }
        else if(monthName.equals("December")){
            month = "12"
        }

        return month
    }


    fun monthNameConvertDays(monthName:String):String{
        var month:String = ""
        if(monthName.equals("January")){
            month = "31"
        }else if(monthName.equals("February")){
            month = "28"
        }
        else if(monthName.equals("March")){
            month = "31"
        }
        else if(monthName.equals("April")){
            month = "30"
        }
        else if(monthName.equals("May")){
            month = "31"
        }
        else if(monthName.equals("June")){
            month = "30"
        }
        else if(monthName.equals("July")){
            month = "31"
        }
        else if(monthName.equals("August")){
            month = "31"
        }
        else if(monthName.equals("September")){
            month = "30"
        }
        else if(monthName.equals("October")){
            month = "31"
        }
        else if(monthName.equals("November")){
            month = "30"
        }
        else if(monthName.equals("December")){
            month = "31"
        }

        return month
    }

}