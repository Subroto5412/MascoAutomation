package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class ApproveListRequest (
        @SerializedName("approveList") var approveList: List<ApproveDataRequest>
        )


data class ApproveDataRequest(
        @SerializedName("ApplyNo") var ApplyNo: Int = 0,
        @SerializedName("EMP_CODE") var EMP_CODE: String = "",
        @SerializedName("ApproveFromDate") var ApproveFromDate: String = "",
        @SerializedName("ApproveToDate") var ApproveToDate: String = "",
        @SerializedName("LeaveNo") var LeaveNo: Int = 0,
        @SerializedName("ApplyDays") var ApplyDays: String = ""
)