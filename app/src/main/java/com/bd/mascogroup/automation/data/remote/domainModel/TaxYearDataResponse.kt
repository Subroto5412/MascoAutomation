package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class TaxYearDataResponse(
        @SerializedName("taxYearNo") var taxYearNo: Int = 0,
        @SerializedName("yearName") var yearName: String = ""
)
