package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.model.db.Leaveapprovallist

class LeaveApprovalListCardData constructor(var leaveapprovallist: Leaveapprovallist) {

    val applyNo : Int
        get() = leaveapprovallist.applyNo

    val emP_CODE : String
        get() = leaveapprovallist.empCode

    val emP_ENAME : String
        get() = leaveapprovallist.empName

    val desigEName : String
        get() = leaveapprovallist.designation

    val applyFromDate : String
        get() = leaveapprovallist.applyFromDate


    val applyToDate : String
        get() = leaveapprovallist.applyToDate

    val applyDays : String
        get() = leaveapprovallist.applyDays

    val leaveNo : Int
        get() = leaveapprovallist.leaveNo

    val leaveType : String
        get() = leaveapprovallist.leaveType

    val leaveMax : Int
        get() = leaveapprovallist.leaveMax

    val leaveAvail : Int
        get() = leaveapprovallist.leaveAvail


    val status : String
        get() = leaveapprovallist.status
}