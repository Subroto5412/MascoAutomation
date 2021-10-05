package com.bd.mascogroup.automation.ui.gpms.hpd

import androidx.databinding.ObservableField
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.LeaveApprovalListCardData


class HPDItemViewModel(
        leaveApprovalListCardData: LeaveApprovalListCardData,
        position: Int,
        listener: leaveApprovalFormItemViewModelListener,
        dataManager: IDataManager
) {
    val mListener: leaveApprovalFormItemViewModelListener
    val mPosition: ObservableField<Int>
    private val mLeaveApprovalListCardData: LeaveApprovalListCardData



    interface leaveApprovalFormItemViewModelListener {}

    init {
        mLeaveApprovalListCardData = leaveApprovalListCardData
        mListener = listener
        mPosition = ObservableField(position)
    }
}