package com.bd.mascogroup.automation.ui.gpms.lwp

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.HourWiseCardData
import com.bd.mascogroup.automation.data.model.domainModel.LineWiseCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.*
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

class LWPViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<ILWPNavigator>(dataManager, ISchedulerProvider) {

    var LWPObserverArrayList: ObservableList<LineWiseCardData> = ObservableArrayList()
    var LWPListLiveData: MutableLiveData<List<LineWiseCardData>> = MutableLiveData()
    private var LWPListItems = ArrayList<LineWiseCardData>()


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

}