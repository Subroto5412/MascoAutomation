package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class TaxDeductionResponse(
        @SerializedName("error") var error: String = "",
        @SerializedName("_taxDeductionsList") var _taxDeductionsList : List<IncomeTaxDeductionResponse>
)
