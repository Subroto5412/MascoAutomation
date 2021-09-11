package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class TaxDeductionRequest(
        @SerializedName("taxYearNo") var taxYearNo: Int = 0
)
