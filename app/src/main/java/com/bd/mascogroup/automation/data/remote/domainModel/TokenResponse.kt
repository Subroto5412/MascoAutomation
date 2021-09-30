package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class TokenResponse(
        @SerializedName("error") var error: String = "",
        @SerializedName("response") var response: String = ""
)
