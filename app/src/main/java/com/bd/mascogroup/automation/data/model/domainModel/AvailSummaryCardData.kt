package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.AvailSummaryResponse
import com.bd.mascogroup.automation.data.remote.domainModel.DailyAttendanceResponse

class AvailSummaryCardData constructor(val ASR : AvailSummaryResponse){

    val sl : String
        get() = ASR.sl

    val leaveType : String
        get() = ASR.leaveType

    val availDay : String
        get() = ASR.availDay

    val fromDate : String
        get() = ASR.fromDate

    val toDate : String
        get() = ASR.toDate

    val applicationDate : String
        get() = ASR.applicationDate
}