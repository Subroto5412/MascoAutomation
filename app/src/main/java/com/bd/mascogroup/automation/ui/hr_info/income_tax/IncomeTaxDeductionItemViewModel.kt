package com.bd.mascogroup.automation.ui.hr_info.income_tax

import androidx.databinding.ObservableField
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.IncomeTaxDeductionCardData

class IncomeTaxDeductionItemViewModel(
        incomeTaxDeductionCardData: IncomeTaxDeductionCardData,
        position: Int,
        listener: IncomeTaxDeductionItemViewModelListener,
        dataManager: IDataManager
) {
    val mListener: IncomeTaxDeductionItemViewModelListener
    val mPosition: ObservableField<Int>
    private val mIncomeTaxDeductionCardData: IncomeTaxDeductionCardData
    val sl: ObservableField<String>
    val month: ObservableField<String>
    val deductionAmount: ObservableField<String>

    interface IncomeTaxDeductionItemViewModelListener {
    }

    init {
        mIncomeTaxDeductionCardData = incomeTaxDeductionCardData
        mListener = listener
        mPosition = ObservableField(position)

        sl = ObservableField(mIncomeTaxDeductionCardData.sl)
        month = ObservableField(mIncomeTaxDeductionCardData.month)
        deductionAmount = ObservableField(mIncomeTaxDeductionCardData.deductionAmount.toString())
    }
}