package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class IncomeTaxDeductionResponse (
        @SerializedName("sl") var sl: String = "",
        @SerializedName("monthYear") var month: String = "",
        @SerializedName("taxDeductionAmount") var deductionAmount: Double = 0.0,
        @SerializedName("taxMonthNo") var taxMonthNo: Int = 0
        )