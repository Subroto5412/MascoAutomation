package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
        @SerializedName("response") val response: String = "",
        @SerializedName("error") val error: String = ""
)
