package com.bd.mascogroup.automation.ui.gpms.hp_details

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.HourlyProductionDetailsCardData
import com.bd.mascogroup.automation.data.model.domainModel.UnitCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.HourlyProductionDetailsData
import com.bd.mascogroup.automation.data.remote.domainModel.HourlyProductionDetailsRequest
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


class HPDetailsViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IHPDetailsNavigator>(dataManager, ISchedulerProvider) {

    var HPDetailsObserverArrayList: ObservableList<HourlyProductionDetailsCardData> = ObservableArrayList()
    var HPDetailsListLiveData: MutableLiveData<List<HourlyProductionDetailsCardData>> = MutableLiveData()
    private var HPDetailsListItems = ArrayList<HourlyProductionDetailsCardData>()

    private var unitCardData = ArrayList<UnitCardData>()
    private var unitName = ArrayList<String>()

    fun getHPDetails(context: Context, unitNo:Int, Date:String,activity_hourly_production_details_sew_output_value_tv:TextView, activity_hourly_production_details_line_input_value_tv_tv:TextView,
                     activity_hourly_production_details_cutting_value_tv:TextView, activity_hourly_production_details_carton_value_tv:TextView, activity_hourly_production_details_total_ploy_value_tv:TextView,
                     activity_hourly_production_details_folding_value_tv:TextView, activity_hourly_production_details_total_iron_value_tv:TextView){
        HPDetailsListItems.clear()

        var sl:Int=0
        var cutting:Double=0.0
        var lineInput:Double=0.0
        var sewingOutput:Double=0.0
        var iron:Double=0.0
        var folding:Double=0.0
        var ploy:Double=0.0
        var cartoon:Double=0.0

        if(UtilMethods.isConnectedToInternet(context)){
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getHourlyProductionDetailsData(HourlyProductionDetailsRequest(unitNo, Date))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ buyerWiseResponse ->
                        buyerWiseResponse._productionDetailsList.forEach {
                            var hourlyProductionDetailsData =  HourlyProductionDetailsData()
                            sl = sl+1
                            cutting = cutting+it.cutting
                            lineInput = lineInput+it.lineInput
                            sewingOutput = sewingOutput+it.sewingOutput
                            iron = iron+it.iron
                            folding = folding+it.folding
                            ploy = ploy+it.ploy
                            cartoon = cartoon+it.cartoon

                            hourlyProductionDetailsData.sl = sl.toString()
                            hourlyProductionDetailsData.timeSlot = it.timeSlot
                            hourlyProductionDetailsData.cutting = it.cutting
                            hourlyProductionDetailsData.lineInput = it.lineInput
                            hourlyProductionDetailsData.sewingOutput = it.sewingOutput
                            hourlyProductionDetailsData.iron = it.iron
                            hourlyProductionDetailsData.folding = it.folding
                            hourlyProductionDetailsData.ploy = it.ploy
                            hourlyProductionDetailsData.cartoon = it.cartoon
                            HPDetailsListItems.add(HourlyProductionDetailsCardData(hourlyProductionDetailsData))
                        }
                        HPDetailsListLiveData.value =HPDetailsListItems
                        activity_hourly_production_details_sew_output_value_tv.setText(sewingOutput.toString())
                        activity_hourly_production_details_line_input_value_tv_tv.setText(lineInput.toString())
                        activity_hourly_production_details_cutting_value_tv.setText(cutting.toString())
                        activity_hourly_production_details_carton_value_tv.setText(cartoon.toString())
                        activity_hourly_production_details_total_ploy_value_tv.setText(ploy.toString())
                        activity_hourly_production_details_folding_value_tv.setText(folding.toString())
                        activity_hourly_production_details_total_iron_value_tv.setText(iron.toString())
                        UtilMethods.hideLoading()
                    }, { error ->
                        activity_hourly_production_details_sew_output_value_tv.setText("")
                        activity_hourly_production_details_line_input_value_tv_tv.setText("")
                        activity_hourly_production_details_cutting_value_tv.setText("")
                        activity_hourly_production_details_carton_value_tv.setText("")
                        activity_hourly_production_details_total_ploy_value_tv.setText("")
                        activity_hourly_production_details_folding_value_tv.setText("")
                        activity_hourly_production_details_total_iron_value_tv.setText("")
                        UtilMethods.hideLoading()
                    }
                    )
        }else{
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun getHPDetailLiveData(): MutableLiveData<List<HourlyProductionDetailsCardData>> {
        return HPDetailsListLiveData
    }

    fun addHPDetailItemToList(Service: List<HourlyProductionDetailsCardData>) {
        HPDetailsObserverArrayList.clear()
        HPDetailsObserverArrayList.addAll(Service)
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
                            AppConstants.HasHPDetailsUnitNameList.add(unit)
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