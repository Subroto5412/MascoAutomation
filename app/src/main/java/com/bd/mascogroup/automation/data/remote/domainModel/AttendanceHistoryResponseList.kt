package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class AttendanceHistoryResponseList(
        @SerializedName("punchDate") var punchDate: String = "",
        @SerializedName("shiftIn") var shiftIn: String = "",
        @SerializedName("shiftOut") var shiftOut: String = "",
        @SerializedName("shiftLate") var shiftLate: String = "",
        @SerializedName("fPunchIn") var fPunchIn: String = "",
        @SerializedName("fPunchOut") var fPunchOut: String = "",
        @SerializedName("shiftName") var shiftName: String = "",
        @SerializedName("additionTime") var additionTime: String = "",
        @SerializedName("fSts") var fSts: String = "",
)
