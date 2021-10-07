package com.bd.mascogroup.automation.ui.gpms.lwp

import androidx.databinding.ObservableField
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.LeaveApprovalListCardData
import com.bd.mascogroup.automation.data.model.domainModel.LineWiseCardData

class LWPItemViewModel(
        lineWiseCardData: LineWiseCardData,
        position: Int,
        listener: LWPItemViewModelListener,
        dataManager: IDataManager
) {
    val mListener: LWPItemViewModelListener
    val mPosition: ObservableField<Int>
    private val mLineWiseCardData: LineWiseCardData
    val sl: ObservableField<String>
    val lineName: ObservableField<String>
    val goodGarments: ObservableField<String>


    interface LWPItemViewModelListener {}

    init {
        mLineWiseCardData = lineWiseCardData
        mListener = listener
        mPosition = ObservableField(position)

        sl = ObservableField(mLineWiseCardData.sl)
        lineName = ObservableField(mLineWiseCardData.lineName)
        goodGarments = ObservableField(mLineWiseCardData.goodGarments.toString())
    }
}