package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class TokenResponse(
        @SerializedName("error") var error: String = "",
        @SerializedName("success") var success: Boolean = false,
        @SerializedName("mac") var mac: String = "",
        @SerializedName("token") var token: String = "",
        @SerializedName("token_id") var token_id: Int = 0
)
