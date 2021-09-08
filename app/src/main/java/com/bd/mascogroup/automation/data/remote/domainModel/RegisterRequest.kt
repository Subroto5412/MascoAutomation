package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
        @SerializedName("emP_CODE") val emP_CODE: String = "",
        @SerializedName("password") val password: String = ""
)
