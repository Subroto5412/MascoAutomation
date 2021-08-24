package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class DailyAttendanceResponse(
        @SerializedName("additionTime") var additionTime: String = "",
        @SerializedName("FPunchIn") var FPunchIn: String = "",
        @SerializedName("FPunchOut") var FPunchOut: String = "",
        @SerializedName("FSts") var FSts: String = "",
        @SerializedName("PunchDate") var PunchDate: String = "",
        @SerializedName("ShiftIn") var ShiftIn: String = "",
        @SerializedName("ShiftLate") var ShiftLate: String = "",
        @SerializedName("ShiftName") var ShiftName: String = "",
        @SerializedName("ShiftOut") var ShiftOut: String = ""
)
