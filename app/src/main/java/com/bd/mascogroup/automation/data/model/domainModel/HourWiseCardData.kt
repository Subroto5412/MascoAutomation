package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.ListHourWiseData

class HourWiseCardData constructor(var HourWiseData: ListHourWiseData) {

    val hour : String
        get() = HourWiseData.hour

    val output : Int
        get() = HourWiseData.output
}