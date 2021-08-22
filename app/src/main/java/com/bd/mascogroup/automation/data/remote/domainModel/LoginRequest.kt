package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
        @SerializedName("userName") var userName: String,
        @SerializedName("password") var password: String
)
