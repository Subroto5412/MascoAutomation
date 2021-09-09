package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class FinancialYearResponse(
        @SerializedName("error") var error: String = "",
        @SerializedName("_listFinalYear") var _listFinalYear: List<FinancialYearDataResponse>
)
