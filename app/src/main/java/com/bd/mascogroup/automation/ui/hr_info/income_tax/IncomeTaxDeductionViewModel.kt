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
import com.bd.mascogroup.automation.data.model.domainModel.LeaveSummaryCardData
import com.bd.mascogroup.automation.data.model.domainModel.TaxYearDataCardData
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.IncomeTaxDeductionResponse
import com.bd.mascogroup.automation.data.remote.domainModel.LeaveSummaryRequest
import com.bd.mascogroup.automation.data.remote.domainModel.TaxDeductionRequest
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

      private var financialYearCardData = ArrayList<TaxYearDataCardData>()
      private var FinancialYearNames = ArrayList<String>()

      fun IncomeTaxDeduction(context: Context, taxYearNo:Int) {
          incomeTaxDeductionListItems.clear()
          var sl:Int = 0
              if (UtilMethods.isConnectedToInternet(context)) {
                  UtilMethods.showLoading(context)
                  val observable = ApiServiceCalling.generalMisApiCallToken().getTaxDeduct(TaxDeductionRequest(taxYearNo))

                  observable.subscribeOn(Schedulers.io())
                          .observeOn(AndroidSchedulers.mainThread())
                          .subscribe({ taxResponse ->
                              taxResponse._taxDeductionsList.forEach {

                                  var incomeTaxDeductionResponse = IncomeTaxDeductionResponse()
                                  sl = sl+1
                                  incomeTaxDeductionResponse.sl = sl.toString()
                                  incomeTaxDeductionResponse.month = it.month
                                  incomeTaxDeductionResponse.deductionAmount = it.deductionAmount

                                  incomeTaxDeductionListItems.add(IncomeTaxDeductionCardData(incomeTaxDeductionResponse))
                              }
                              incomeTaxDeductionListLiveData.value = incomeTaxDeductionListItems

                              UtilMethods.hideLoading()
                          }, { error ->
                              UtilMethods.hideLoading()
                          }
                          )
              } else {
                  UtilMethods.showLongToast(context, "No Internet Connection!")
              }
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
              val observable = ApiServiceCalling.generalMisApiCall().getTaxYear()

              observable.subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe({ yearResponse ->

                          yearResponse._taxYearList.forEach {
                              financialYearCardData.add(TaxYearDataCardData(it))
                          }
                          FinancialYearNames.clear()
                          for (i in 0 until financialYearCardData.size) {
                              val fYear = HashMap<String, String>()

                              fYear.put("taxYearNo", "" + financialYearCardData.get(i).taxYearNo)
                              fYear.put("yearName", financialYearCardData.get(i).yearName)

                              AppConstants.HasYearTaxList.add(fYear)
                              FinancialYearNames.add(financialYearCardData.get(i).yearName)
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
                      }
                      )
          } else {
              UtilMethods.showLongToast(context, "No Internet Connection!")
          }
      }
}