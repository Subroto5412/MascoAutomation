package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class HourlyProductionDetailsRequest (
        @SerializedName("unit_no") var unit_no: Int = 0,
        @SerializedName("created_date") var created_date: String = ""
        )