package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class LoginImageResponse (
        @SerializedName("unitEName") val unitEName: String = "",
        @SerializedName("emP_ENAME") val emP_ENAME: String = "",
        @SerializedName("serverFileName") val serverFileName: String = "",
        @SerializedName("error") val error: String = ""
        )