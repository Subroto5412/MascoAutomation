package com.bd.mascogroup.automation.ui.hr_info.income_tax

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.IncomeTaxDeductionCardData
import com.bd.mascogroup.automation.data.remote.domainModel.IncomeTaxDeductionResponse
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import java.util.ArrayList
import javax.inject.Inject

  class IncomeTaxDeductionViewModel @Inject constructor(
            dataManager: IDataManager,
            ISchedulerProvider: ISchedulerProvider
    ): BaseViewModel<IIncomeTaxDeductionNavigator>(dataManager, ISchedulerProvider) {

      var incomeTaxDeductionObserverArrayList: ObservableList<IncomeTaxDeductionCardData> = ObservableArrayList()
      var incomeTaxDeductionListLiveData: MutableLiveData<List<IncomeTaxDeductionCardData>> = MutableLiveData()
      private var incomeTaxDeductionListItems = ArrayList<IncomeTaxDeductionCardData>()

      fun IncomeTaxDeduction(context: Context) {

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
}