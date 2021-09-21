package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class DailyAttendanceSummaryResponse (
        @SerializedName("error") var error: String = "",
        @SerializedName("_listLeaveCount") var allLeaveCount: List<DailyAttendanceStatusResponse>
        )