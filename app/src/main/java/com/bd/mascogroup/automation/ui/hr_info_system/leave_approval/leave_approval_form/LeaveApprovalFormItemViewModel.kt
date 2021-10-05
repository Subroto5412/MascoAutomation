package com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.leave_approval_form

import android.content.Context
import android.content.SharedPreferences
import androidx.databinding.ObservableField
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.LeaveApprovalListCardData
import com.bd.mascogroup.automation.data.model.domainModel.LeaveSummaryCardData

class LeaveApprovalFormItemViewModel(
        leaveApprovalListCardData: LeaveApprovalListCardData,
        position: Int,
        listener: leaveApprovalFormItemViewModelListener,
        dataManager: IDataManager,
        context: Context
) {
    val mListener: leaveApprovalFormItemViewModelListener
    val mPosition: ObservableField<Int>
    private val mLeaveApprovalListCardData: LeaveApprovalListCardData
    val emP_CODE: ObservableField<String>
    val emP_ENAME: ObservableField<String>
    val desigEName: ObservableField<String>
    val applyFromDate: ObservableField<String>
    val applyToDate: ObservableField<String>
    val applyDays: ObservableField<String>
    val leaveType: ObservableField<String>
    val leaveAvail: ObservableField<String>
    val check: ObservableField<Boolean>


    interface leaveApprovalFormItemViewModelListener {}

    init {
        mLeaveApprovalListCardData = leaveApprovalListCardData
        mListener = listener
        mPosition = ObservableField(position)

        emP_CODE = ObservableField(mLeaveApprovalListCardData.emP_CODE)
        emP_ENAME = ObservableField(mLeaveApprovalListCardData.emP_ENAME)
        desigEName = ObservableField(mLeaveApprovalListCardData.desigEName)
        applyFromDate = ObservableField(mLeaveApprovalListCardData.applyFromDate)
        applyToDate = ObservableField(mLeaveApprovalListCardData.applyToDate)
        applyDays = ObservableField("AP-"+mLeaveApprovalListCardData.applyDays+"day")
        leaveType = ObservableField(mLeaveApprovalListCardData.leaveType+"-"+mLeaveApprovalListCardData.leaveAvail+"day")
        leaveAvail = ObservableField("Bl-"+(mLeaveApprovalListCardData.leaveAvail-mLeaveApprovalListCardData.applyDays.toInt()).toString()+"day")

        var PRIVATE_MODE = 0
        val sharedPref: SharedPreferences = context.getSharedPreferences(mLeaveApprovalListCardData.emP_CODE, PRIVATE_MODE)
        check = ObservableField(sharedPref.getString(mLeaveApprovalListCardData.emP_CODE, "").toBoolean())
    }
}