package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
        @SerializedName("response") val response: Boolean = false,
        @SerializedName("error") val error: String = ""
)
