package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class LeavePendingListData(
        @SerializedName("applyNo") var applyNo: Int = 0,
        @SerializedName("emP_CODE") var emP_CODE: String = "",
        @SerializedName("emP_ENAME") var emP_ENAME: String = "",
        @SerializedName("desigEName") var desigEName: String = "",
        @SerializedName("applyFromDate") var applyFromDate: String = "",
        @SerializedName("applyToDate") var applyToDate: String = "",
        @SerializedName("applyDays") var applyDays: String = "",
        @SerializedName("leaveNo") var leaveNo: Int = 0,
        @SerializedName("leaveType") var leaveType: String = "",
        @SerializedName("leaveMax") var leaveMax: Int = 0,
        @SerializedName("leaveAvail") var leaveAvail: Int = 0
)
