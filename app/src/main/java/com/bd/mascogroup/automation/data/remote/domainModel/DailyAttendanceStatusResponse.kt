package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class DailyAttendanceStatusResponse(
    @SerializedName("status") var status: String = "",
    @SerializedName("statusValue") var statusValue: String = "")
