package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class LeaveSummaryDataResponse (
        @SerializedName("type_name") var type_name: String = "",
        @SerializedName("cl") var cl: String = "",
        @SerializedName("sl") var sl: String = "",
        @SerializedName("el") var el: String = "")