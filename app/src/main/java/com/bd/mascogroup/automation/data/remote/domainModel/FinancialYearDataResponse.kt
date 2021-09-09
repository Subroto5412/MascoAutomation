package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class FinancialYearDataResponse (
        @SerializedName("finalYearNo") var finalYearNo: Int = 0,
        @SerializedName("finalYearName") var finalYearName: String = "",
        @SerializedName("yearName") var yearName: String = ""
        )