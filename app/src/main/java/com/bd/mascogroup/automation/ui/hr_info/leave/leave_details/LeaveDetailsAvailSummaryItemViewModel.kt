package com.bd.mascogroup.automation.ui.hr_info.leave.leave_details

import androidx.databinding.ObservableField
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.AvailSummaryCardData

class LeaveDetailsAvailSummaryItemViewModel(
        availSummaryCardData: AvailSummaryCardData,
        position: Int,
        listener: availSummaryListItemViewModelListener,
        dataManager: IDataManager
) {
    val mListener: availSummaryListItemViewModelListener
    val mPosition: ObservableField<Int>
    private val mAvailSummaryCardData: AvailSummaryCardData
    val sl: ObservableField<String>
    val leaveType: ObservableField<String>
    val availDay: ObservableField<String>
    val fromDay: ObservableField<String>
    val toDay: ObservableField<String>
    val applicationDay: ObservableField<String>

    interface availSummaryListItemViewModelListener {}

    init {
        mAvailSummaryCardData = availSummaryCardData
        mListener = listener
        mPosition = ObservableField(position)

        sl = ObservableField(mAvailSummaryCardData.sl)
        leaveType = ObservableField(mAvailSummaryCardData.leaveType)
        availDay = ObservableField(mAvailSummaryCardData.availDay.toString())
        fromDay = ObservableField(mAvailSummaryCardData.fromDate)
        toDay = ObservableField(mAvailSummaryCardData.toDate)
        applicationDay = ObservableField(mAvailSummaryCardData.applicationDate)
    }
}