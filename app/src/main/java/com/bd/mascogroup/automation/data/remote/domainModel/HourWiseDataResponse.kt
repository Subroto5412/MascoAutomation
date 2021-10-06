package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class HourWiseDataResponse(
        @SerializedName("error") var error: String="",
        @SerializedName("_hourWiseDataList") var _hourWiseDataList: List<ListHourWiseData>
)
data class ListHourWiseData(
        @SerializedName("hour") var hour: String="",
        @SerializedName("output") var output: Int = 0
)

