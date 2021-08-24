package com.bd.mascogroup.automation.ui.hr_info.daily_attendance

import android.R
import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.DailyAttendanceResponse
import com.bd.mascogroup.automation.ui.base.BaseViewModel
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


    var dailyAttendanceObserverArrayList: ObservableList<DailyAttendanceCardData> = ObservableArrayList()
    var dailyAttendanceListLiveData: MutableLiveData<List<DailyAttendanceCardData>> = MutableLiveData()
    private var dailyAttendanceListItems = ArrayList<DailyAttendanceCardData>()


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

        /*if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalWebApiCall().getDeiveryTimeSlot()

            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ slotResponse ->

                    slotResponse.data.forEach {
                        Log.e("---------","----date-----"+it.date)
                        var date1 = it.date
                        var date2 = it.date
                        it.slot.forEach {
                            val dateSlot = DeliveryTimeSlot()

                            dateSlot.id = 1
                            dateSlot.from_time = date1
                            dateSlot.to_time = date2

                           *//* Log.e("---------","----id-----"+dateSlot.id)
                            Log.e("---------","----from_time-----"+dateSlot.from_time)
                            Log.e("---------","----to_time-----"+dateSlot.to_time)*//*

                            dailyAttendanceListItems.add(DailyAttendanceCardData(dateSlot))
                        }
                        dailyAttendanceListLiveData.value = dailyAttendanceListItems
                    }


                    for (i in 0 until dailyAttendanceListItems.size) {
                         Log.e("----bbb-----","----id-----"+dailyAttendanceListItems.get(i).id)
                        Log.e("-----bb----","----from_time-----"+dailyAttendanceListItems.get(i).FPunchIn)
                        Log.e("----bb-----","----to_time-----"+dailyAttendanceListItems.get(i).FPunchOut)
                    }


                    UtilMethods.hideLoading()
                }, { error ->
                    UtilMethods.hideLoading()
                    //  UtilMethods.showLongToast(context, error.message.toString())
                }
                )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
*/



    }

    fun getdailyAttendanceLiveData(): MutableLiveData<List<DailyAttendanceCardData>> {
        return dailyAttendanceListLiveData
    }

    fun addDailyAttendanceItemToList(Service: List<DailyAttendanceCardData>) {
        dailyAttendanceObserverArrayList.clear()
        dailyAttendanceObserverArrayList.addAll(Service)
    }
}