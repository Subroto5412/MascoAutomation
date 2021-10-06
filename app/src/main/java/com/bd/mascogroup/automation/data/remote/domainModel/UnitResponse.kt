package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class UnitResponse(
        @SerializedName("error") var error: String = "",
        @SerializedName("_listUnitName") var _listUnitName: List<listUnitName>
)

data class listUnitName(
        @SerializedName("unitNo") var unitNo: Int = 0,
        @SerializedName("unitEName") var unitEName: String = ""
)