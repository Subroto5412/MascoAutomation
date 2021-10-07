package com.bd.mascogroup.automation.ui.gpms.hpd

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.BuyerWiseCardData
import com.bd.mascogroup.automation.data.model.domainModel.HourWiseCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.*
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class HPDViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IHPDNavigator>(dataManager, ISchedulerProvider) {

    var HPDObserverArrayList: ObservableList<HourWiseCardData> = ObservableArrayList()
    var HPDListLiveData: MutableLiveData<List<HourWiseCardData>> = MutableLiveData()
    private var HPDListItems = ArrayList<HourWiseCardData>()


    fun getHPD(context: Context, unitNo:Int, Date:String){
        HPDListItems.clear()

        var sl:Int=0
        if(UtilMethods.isConnectedToInternet(context)){
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getHourWiseData(HourWiseDataRequest(unitNo, Date))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ hourlyWiseResponse ->
                        hourlyWiseResponse._hourWiseDataList.forEach {
                            var ListHourWiseData =  ListHourWiseData()
                            sl = sl+1
                            ListHourWiseData.sl = sl.toString()
                            ListHourWiseData.hour = it.hour
                            ListHourWiseData.output = it.output
                            HPDListItems.add(HourWiseCardData(ListHourWiseData))
                        }
                        HPDListLiveData.value = HPDListItems
                        UtilMethods.hideLoading()
                    }, { error ->
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
}