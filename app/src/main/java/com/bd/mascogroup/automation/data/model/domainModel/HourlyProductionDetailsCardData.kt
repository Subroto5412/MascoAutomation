package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.HourlyProductionDetailsData

class HourlyProductionDetailsCardData constructor(var HourlyProductionDetail: HourlyProductionDetailsData) {

    val sl : String
        get() = HourlyProductionDetail.sl

    val hour : String
        get() = HourlyProductionDetail.timeSlot

    val cutting : Int
        get() = HourlyProductionDetail.cutting

    val lineInput : Int
        get() = HourlyProductionDetail.lineInput

    val sewingOutput : Int
        get() = HourlyProductionDetail.sewingOutput

    val iron : Int
        get() = HourlyProductionDetail.iron

    val folding : Int
        get() = HourlyProductionDetail.folding

    val ploy : Int
        get() = HourlyProductionDetail.ploy

    val cartoon : Int
        get() = HourlyProductionDetail.cartoon
}