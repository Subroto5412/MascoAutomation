package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class TaxYearResponse(
        @SerializedName("error") var error: String = "",
        @SerializedName("_taxYearList") var _taxYearList : List<TaxYearDataResponse>
)
