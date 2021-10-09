package com.bd.mascogroup.automation.ui.gpms.hpd

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.BuyerWiseCardData
import com.bd.mascogroup.automation.data.model.domainModel.HourWiseCardData
import com.bd.mascogroup.automation.data.model.domainModel.UnitCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.*
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class HPDViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IHPDNavigator>(dataManager, ISchedulerProvider) {

    var HPDObserverArrayList: ObservableList<HourWiseCardData> = ObservableArrayList()
    var HPDListLiveData: MutableLiveData<List<HourWiseCardData>> = MutableLiveData()
    private var HPDListItems = ArrayList<HourWiseCardData>()

    private var unitCardData = ArrayList<UnitCardData>()
    private var unitName = ArrayList<String>()

    fun getHPD(context: Context, unitNo:Int, Date:String, activity_hourly_wise_output_value_tv:TextView){
        HPDListItems.clear()

        var sl:Int=0
        var output:Double=0.0
        if(UtilMethods.isConnectedToInternet(context)){
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getHourWiseData(HourWiseDataRequest(unitNo, Date))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ hourlyWiseResponse ->
                        hourlyWiseResponse._hourWiseDataList.forEach {
                            var ListHourWiseData =  ListHourWiseData()
                            sl = sl+1
                            output = output+it.output
                            ListHourWiseData.sl = sl.toString()
                            ListHourWiseData.hour = it.hour
                            ListHourWiseData.output = it.output
                            HPDListItems.add(HourWiseCardData(ListHourWiseData))
                        }
                        HPDListLiveData.value = HPDListItems
                        activity_hourly_wise_output_value_tv.setText(output.toString())

                        UtilMethods.hideLoading()
                    }, { error ->
                        activity_hourly_wise_output_value_tv.setText("")
                        UtilMethods.hideLoading()
                    }
                    )
        }else{
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun getHPDLiveData(): MutableLiveData<List<HourWiseCardData>> {
        return HPDListLiveData
    }

    fun addHPDItemToList(Service: List<HourWiseCardData>) {
        HPDObserverArrayList.clear()
        HPDObserverArrayList.addAll(Service)
    }

    fun getUnitName(
            context: Context,
            unitNameSp: Spinner
    ) {
        unitCardData.clear()
        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getUnitName()

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ unitResponse ->

                        unitResponse._listUnitName.forEach {
                            unitCardData.add(UnitCardData(it))
                        }
                        unitName.clear()
                        for (i in 0 until unitCardData.size) {
                            val unit = HashMap<String, String>()
                            unit.put("unitNo", unitCardData.get(i).unitNo.toString())
                            unit.put("unitEName", "" + unitCardData.get(i).unitEName)
                            AppConstants.HasHPDUnitNameList.add(unit)
                            unitName.add(unitCardData.get(i).unitEName)
                        }
                        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                                context,
                                R.layout.simple_spinner_item,
                                unitName
                        )
                        spinnerArrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item) // The drop down view
                        unitNameSp.setAdapter(spinnerArrayAdapter)

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

    fun getCurrentDate():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(Date())
    }
}