package com.bd.mascogroup.automation.ui.hr_info.income_tax

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.FinancialYearCardData
import com.bd.mascogroup.automation.data.model.domainModel.IncomeTaxDeductionCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.IncomeTaxDeductionResponse
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

  class IncomeTaxDeductionViewModel @Inject constructor(
            dataManager: IDataManager,
            ISchedulerProvider: ISchedulerProvider
    ): BaseViewModel<IIncomeTaxDeductionNavigator>(dataManager, ISchedulerProvider) {

      var incomeTaxDeductionObserverArrayList: ObservableList<IncomeTaxDeductionCardData> = ObservableArrayList()
      var incomeTaxDeductionListLiveData: MutableLiveData<List<IncomeTaxDeductionCardData>> = MutableLiveData()
      private var incomeTaxDeductionListItems = ArrayList<IncomeTaxDeductionCardData>()

      private var financialYearCardData = ArrayList<FinancialYearCardData>()
      private var FinancialYearNames = ArrayList<String>()

      fun IncomeTaxDeduction(context: Context, fYEarSpId:Int) {

          val IncomeTaxDeductionResponse2 = IncomeTaxDeductionResponse()
          IncomeTaxDeductionResponse2.sl = "1"
          IncomeTaxDeductionResponse2.month = "August-20"
          IncomeTaxDeductionResponse2.deductionAmount = "400"

          val IncomeTaxDeductionResponse = IncomeTaxDeductionResponse()
          IncomeTaxDeductionResponse.sl = "1"
          IncomeTaxDeductionResponse.month = "July-20"
          IncomeTaxDeductionResponse.deductionAmount = "400"

          incomeTaxDeductionListItems.add(IncomeTaxDeductionCardData(IncomeTaxDeductionResponse))
          incomeTaxDeductionListItems.add(IncomeTaxDeductionCardData(IncomeTaxDeductionResponse2))
          incomeTaxDeductionListLiveData.value = incomeTaxDeductionListItems

      }

      fun getincomeTaxDeductionLiveData(): MutableLiveData<List<IncomeTaxDeductionCardData>> {
          return incomeTaxDeductionListLiveData
      }

      fun addIncomeTaxDeductionItemToList(Service: List<IncomeTaxDeductionCardData>) {
          incomeTaxDeductionObserverArrayList.clear()
          incomeTaxDeductionObserverArrayList.addAll(Service)
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