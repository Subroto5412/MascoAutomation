package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class LineWiseResponse(
        @SerializedName("_lineWiseProduction") var _lineWiseProduction: List<LineWiseProduction>,
        @SerializedName("error") var error: String=""
)

data class LineWiseProduction(
        @SerializedName("goodGarments") var goodGarments: Int = 0,
        @SerializedName("lineName") var lineName: String=""
)