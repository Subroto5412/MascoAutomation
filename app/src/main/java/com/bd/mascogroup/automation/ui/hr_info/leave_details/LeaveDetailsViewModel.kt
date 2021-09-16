package com.bd.mascogroup.automation.ui.hr_info.leave_details

import android.R
import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.AvailSummaryCardData
import com.bd.mascogroup.automation.data.model.domainModel.FinancialYearCardData
import com.bd.mascogroup.automation.data.model.domainModel.LeaveSummaryCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.AvailSummaryDataResponse
import com.bd.mascogroup.automation.data.remote.domainModel.AvailSummaryRequest
import com.bd.mascogroup.automation.data.remote.domainModel.LeaveSummaryRequest
import com.bd.mascogroup.automation.data.remote.domainModel.RefreshTokenRequest
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap
import kotlin.collections.List
import kotlin.collections.forEach

class LeaveDetailsViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
) : BaseViewModel<ILeaveDetailsNavigator>(dataManager, ISchedulerProvider) {

    private var financialYearCardData = ArrayList<FinancialYearCardData>()
    private var FinancialYearNames = ArrayList<String>()

    var leaveSummaryObserverArrayList: ObservableList<LeaveSummaryCardData> = ObservableArrayList()
    var leaveSummaryListLiveData: MutableLiveData<List<LeaveSummaryCardData>> = MutableLiveData()
    private var leaveSummaryListItems = ArrayList<LeaveSummaryCardData>()

    var availSummaryObserverArrayList: ObservableList<AvailSummaryCardData> = ObservableArrayList()
    var availSummaryListLiveData: MutableLiveData<List<AvailSummaryCardData>> = MutableLiveData()
    private var availSummaryListItems = ArrayList<AvailSummaryCardData>()

    fun leaveSummary(context: Context, fYEarSpId: Int) {
        leaveSummaryListItems.clear()
        availSummaryListItems.clear()
        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCallToken().getLeaveHistory(LeaveSummaryRequest(fYEarSpId))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ leaveSummaryResponse ->
                        leaveSummaryResponse._LeaveHistoryformatList.forEach {
                            leaveSummaryListItems.add(LeaveSummaryCardData(it))
                        }
                        leaveSummaryListLiveData.value = leaveSummaryListItems

                        UtilMethods.hideLoading()
                        availSummary(context, fYEarSpId)
                    }, { error ->
                        UtilMethods.hideLoading()
//                        UtilMethods.showLongToast(context, error.message.toString())
//                        availSummary(context, fYEarSpId)
                        if (error.message.toString().contains("401")){
                            getRefreshToken(context)
                        }
                    }
                    )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
    }

    fun availSummary(context: Context, fYEarSpId: Int) {
        availSummaryListItems.clear()

        var sl:Int = 0
        if (UtilMethods.isConnectedToInternet(context)) {
            UtilMethods.showLoading(context)
            val observable = ApiServiceCalling.generalMisApiCallToken().getAvailHistory(AvailSummaryRequest(fYEarSpId))

            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ availSummaryResponse ->
                        availSummaryResponse._availHistoryList.forEach {
                          var  availSummaryDataResponse = AvailSummaryDataResponse()
                            sl = sl+1
                            it.sl = sl.toString()
                            availSummaryListItems.add(AvailSummaryCardData(it))
                        }
                        availSummaryListLiveData.value = availSummaryListItems

                        UtilMethods.hideLoading()
                    }, { error ->
                        UtilMethods.hideLoading()
                        if (error.message.toString().contains("401")){
                            getRefreshToken(context)
                        }
//                         UtilMethods.showLongToast(context, error.message.toString())
                    }
                    )
        } else {
            UtilMethods.showLongToast(context, "No Internet Connection!")
        }
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

                            AppConstants.HasYearForLeaveList.add(fYear)
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
}