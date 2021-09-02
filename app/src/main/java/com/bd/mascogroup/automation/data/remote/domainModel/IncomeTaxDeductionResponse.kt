package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class IncomeTaxDeductionResponse (
        @SerializedName("sl") var sl: String = "",
        @SerializedName("month") var month: String = "",
        @SerializedName("deductionAmount") var deductionAmount: String = ""
        )