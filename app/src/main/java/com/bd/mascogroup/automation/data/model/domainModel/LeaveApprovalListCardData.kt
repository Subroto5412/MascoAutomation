package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.LeavePendingListData

class LeaveApprovalListCardData constructor(var leavePendingListData: LeavePendingListData) {

    val applyNo : Int
        get() = leavePendingListData.applyNo

    val emP_CODE : String
        get() = leavePendingListData.emP_CODE

    val emP_ENAME : String
        get() = leavePendingListData.emP_ENAME

    val desigEName : String
        get() = leavePendingListData.desigEName

    val applyFromDate : String
        get() = leavePendingListData.applyFromDate


    val applyToDate : String
        get() = leavePendingListData.applyToDate

    val applyDays : String
        get() = leavePendingListData.applyDays

    val leaveNo : Int
        get() = leavePendingListData.leaveNo

    val leaveType : String
        get() = leavePendingListData.leaveType

    val leaveMax : Int
        get() = leavePendingListData.leaveMax

    val leaveAvail : Int
        get() = leavePendingListData.leaveAvail
}