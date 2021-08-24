package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class DailyAttendanceRequest(
        @SerializedName("additionTime") var additionTime: String
)
