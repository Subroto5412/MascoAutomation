package com.bd.mascogroup.automation.ui.gpms.lwp

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.FinancialYearCardData
import com.bd.mascogroup.automation.data.model.domainModel.HourWiseCardData
import com.bd.mascogroup.automation.data.model.domainModel.LineWiseCardData
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

class LWPViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ILWPNavigator>(dataManager, ISchedulerProvider) {

    var LWPObserverArrayList: ObservableList<LineWiseCardData> = ObservableArrayList()
    var LWPListLiveData: MutableLiveData<List<LineWiseCardData>> = MutableLiveData()
    private var LWPListItems = ArrayList<LineWiseCardData>()

    private var unitCardData = ArrayList<UnitCardData>()
    private var unitName = ArrayList<String>()

    fun getLWP(context: Context, unitNo:Int, Date:String){
        LWPListItems.clear()

        var sl:Int=0
        if(UtilMethods.isConnectedToInternet(context)){
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getLineWiseData(LineWiseRequest(unitNo, Date))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ hourlyWiseResponse ->
                        hourlyWiseResponse._lineWiseProduction.forEach {
                            var LineWise =  LineWiseProduction()
                            sl = sl+1
                            LineWise.sl = sl.toString()
                            LineWise.lineName = it.lineName
                            LineWise.goodGarments = it.goodGarments
                            LWPListItems.add(LineWiseCardData(LineWise))
                        }
                        LWPListLiveData.value = LWPListItems
                        UtilMethods.hideLoading()
                    }, { error ->
                        UtilMethods.hideLoading()
                    }
                    )
        }else{
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun getLWPLiveData(): MutableLiveData<List<LineWiseCardData>> {
        return LWPListLiveData
    }

    fun addLWPItemToList(Service: List<LineWiseCardData>) {
        LWPObserverArrayList.clear()
        LWPObserverArrayList.addAll(Service)
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
                            AppConstants.HasLWPUnitNameList.add(unit)
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