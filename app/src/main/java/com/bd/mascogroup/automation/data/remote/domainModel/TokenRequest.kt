package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class TokenRequest (
        @SerializedName("token") val token: String = ""
        )