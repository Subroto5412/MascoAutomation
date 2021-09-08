package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginRequest(
        @SerializedName("empId") var empId: String,
        @SerializedName("password") var password: String
)
