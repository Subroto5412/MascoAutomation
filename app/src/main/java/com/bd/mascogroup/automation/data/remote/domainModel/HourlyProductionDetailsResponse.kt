package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class HourlyProductionDetailsResponse(
        @SerializedName("error") var error: String="",
        @SerializedName("_productionDetailsList") var _productionDetailsList: List<HourlyProductionDetailsData>
)

data class HourlyProductionDetailsData(
        @SerializedName("sl") var sl: String="",
        @SerializedName("timeSlot") var timeSlot: String="",
        @SerializedName("cutting") var cutting: Int=0,
        @SerializedName("swingOutput") var sewingOutput: Int=0,
        @SerializedName("lineInput") var lineInput: Int=0,
        @SerializedName("iron") var iron: Int=0,
        @SerializedName("folder") var folding: Int=0,
        @SerializedName("ploy") var ploy: Int=0,
        @SerializedName("cartoon") var cartoon: Int=0
)

