package com.bd.mascogroup.automation.ui.hr_info.leave.leave_details

import androidx.databinding.ObservableField
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.LeaveSummaryCardData

class LeaveDetailsLeaveSummaryItemViewModel(
        leaveSummaryCardData: LeaveSummaryCardData,
        position: Int,
        listener: leaveSummaryListItemViewModelListener,
        dataManager: IDataManager
) {
    val mListener: leaveSummaryListItemViewModelListener
    val mPosition: ObservableField<Int>
    private val mLeaveSummaryCardData: LeaveSummaryCardData
    val type_name: ObservableField<String>
    val cl: ObservableField<String>
    val sl: ObservableField<String>
    val el: ObservableField<String>

    interface leaveSummaryListItemViewModelListener {}

    init {
        mLeaveSummaryCardData = leaveSummaryCardData
        mListener = listener
        mPosition = ObservableField(position)

        type_name = ObservableField(mLeaveSummaryCardData.type_name)
        cl = ObservableField(mLeaveSummaryCardData.cl)
        sl = ObservableField(mLeaveSummaryCardData.sl)
        el = ObservableField(mLeaveSummaryCardData.el)
    }
}