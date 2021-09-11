package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class AvailSummaryDataResponse (
        @SerializedName("sl") var sl: String = "",
        @SerializedName("leaveType") var leaveType: String="",
        @SerializedName("availDays") var availDay: Int=0,
        @SerializedName("approveFromDate") var fromDate: String="",
        @SerializedName("approveToDate") var toDate: String="",
        @SerializedName("applicationDate") var applicationDate: String=""
        )