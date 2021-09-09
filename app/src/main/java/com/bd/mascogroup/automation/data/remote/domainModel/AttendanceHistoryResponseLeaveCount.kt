package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class AttendanceHistoryResponseLeaveCount(
        @SerializedName("empId") var empId: Int=0,
        @SerializedName("l") var l: Int=0,
        @SerializedName("a") var a: Int=0,
        @SerializedName("h") var h: Int=0,
        @SerializedName("cl") var cl: Int=0,
        @SerializedName("p") var p: Int=0,
        @SerializedName("qo") var qo: Int=0,
        @SerializedName("sl") var sl: Int=0,
        @SerializedName("el") var el: Int=0,
        @SerializedName("lwp") var lwp: Int=0,
        @SerializedName("ot") var ot: Int=0,
        @SerializedName("osd") var osd: Int=0,
        @SerializedName("ha") var ha: Int=0
)
