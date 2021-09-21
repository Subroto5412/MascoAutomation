package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class AttendanceHistoryResponse(
        @SerializedName("error") var error: String = "",
        @SerializedName("_attHistoryListStr") var _attHistoryListStr: List<AttendanceHistoryResponseList>,
//        @SerializedName("_allLeaveCountList") var allLeaveCount: List<DailyAttendanceStatusResponse>
)
