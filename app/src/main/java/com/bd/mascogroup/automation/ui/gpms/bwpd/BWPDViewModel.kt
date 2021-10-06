package com.bd.mascogroup.automation.ui.gpms.bwpd

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.BuyerWiseCardData
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceStatusCardData
import com.bd.mascogroup.automation.data.model.domainModel.LeaveSummaryCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.BuyerWiseDataRequest
import com.bd.mascogroup.automation.data.remote.domainModel.DailyAttendanceRequest
import com.bd.mascogroup.automation.data.remote.domainModel.ListBuyerWiseData
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject


class BWPDViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IBWPDNavigator>(dataManager, ISchedulerProvider) {

    var BWPDObserverArrayList: ObservableList<BuyerWiseCardData> = ObservableArrayList()
    var BWPDListLiveData: MutableLiveData<List<BuyerWiseCardData>> = MutableLiveData()
    private var BWPDListItems = ArrayList<BuyerWiseCardData>()


    fun getBWPD(context: Context, unitNo:Int, Date:String){
        BWPDListItems.clear()

        var sl:Int=0
        if(UtilMethods.isConnectedToInternet(context)){
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCall().getBuyerWiseData(BuyerWiseDataRequest(unitNo, Date))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ buyerWiseResponse ->
                        buyerWiseResponse._listBuyerWiseData.forEach {
                           var ListBuyerWiseData =  ListBuyerWiseData()
                            sl = sl+1
                            ListBuyerWiseData.buyerId = sl
                            ListBuyerWiseData.buyerName = it.buyerName
                            ListBuyerWiseData.styleNo = it.styleNo
                            ListBuyerWiseData.orderNo = it.orderNo
                            ListBuyerWiseData.orderQty = it.orderQty
                            ListBuyerWiseData.sewingQty = it.sewingQty
                            ListBuyerWiseData.balance = it.balance
                            BWPDListItems.add(BuyerWiseCardData(ListBuyerWiseData))
                        }
                        BWPDListLiveData.value = BWPDListItems
                        UtilMethods.hideLoading()
                    }, { error ->
                        UtilMethods.hideLoading()
                    }
                    )
        }else{
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun getBWPDLiveData(): MutableLiveData<List<BuyerWiseCardData>> {
        return BWPDListLiveData
    }

    fun addBWPDItemToList(Service: List<BuyerWiseCardData>) {
        BWPDObserverArrayList.clear()
        BWPDObserverArrayList.addAll(Service)
    }
}