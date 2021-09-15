package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class RefreshTokenResponse(
        @SerializedName("rftoken") val rftoken: rftokenResponse,
        @SerializedName("error") val error: String = ""
)

data class rftokenResponse(
        @SerializedName("jwtToken") val jwtToken: String = "",
        @SerializedName("refresh_token") val refresh_token: String = ""
)