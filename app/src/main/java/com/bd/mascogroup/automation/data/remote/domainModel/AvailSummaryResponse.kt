package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class AvailSummaryResponse (
        @SerializedName("sl") var sl: String = "",
        @SerializedName("leaveType") var leaveType: String="",
        @SerializedName("availDay") var availDay: String="",
        @SerializedName("fromDate") var fromDate: String="",
        @SerializedName("toDate") var toDate: String="",
        @SerializedName("applicationDate") var applicationDate: String=""
        )