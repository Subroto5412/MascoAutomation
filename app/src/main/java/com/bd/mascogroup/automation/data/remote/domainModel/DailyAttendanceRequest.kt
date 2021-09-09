package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class DailyAttendanceRequest(
        @SerializedName("fromDate") var fromDate: String="",
        @SerializedName("toDate") var toDate: String=""
)
