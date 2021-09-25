package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class AttendanceHistoryResponseList(
        @SerializedName("datePunch") var punchDate: String = "",
        @SerializedName("shiftInTime") var shiftIn: String = "",
        @SerializedName("shiftOutTime") var shiftOut: String = "",
        @SerializedName("shiftLateTime") var shiftLate: String = "",
        @SerializedName("punchInTime") var fPunchIn: String = "",
        @SerializedName("punchOutTime") var fPunchOut: String = "",
        @SerializedName("shiftName") var shiftName: String = "",
        @SerializedName("lateTime") var lateTime: String = "",
        @SerializedName("additionalTime") var additionTime: String = "",
        @SerializedName("fSts") var fSts: String = "",
)
