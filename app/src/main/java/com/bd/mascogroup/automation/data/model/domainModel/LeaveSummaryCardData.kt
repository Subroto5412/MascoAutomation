package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.LeaveSummaryDataResponse

class LeaveSummaryCardData constructor(val LSR : LeaveSummaryDataResponse) {

    val type_name : String
        get() = LSR.type_name

    val cl : String
        get() = LSR.cl

    val sl : String
        get() = LSR.sl

    val el : String
        get() = LSR.el
}