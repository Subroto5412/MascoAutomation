package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class RefreshTokenRequest(
        @SerializedName("jwtToken") val jwtToken: String = "",
        @SerializedName("refresh_token") val refresh_token: String = ""
)
