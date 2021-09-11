package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.AvailSummaryDataResponse

class AvailSummaryCardData constructor(val ASR : AvailSummaryDataResponse){

    val sl : String
        get() = ASR.sl

    val leaveType : String
        get() = ASR.leaveType

    val availDay : Int
        get() = ASR.availDay

    val fromDate : String
        get() = ASR.fromDate

    val toDate : String
        get() = ASR.toDate

    val applicationDate : String
        get() = ASR.applicationDate
}