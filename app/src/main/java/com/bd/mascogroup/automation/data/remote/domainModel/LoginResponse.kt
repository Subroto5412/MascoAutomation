package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
        @SerializedName("empCode") val empCode: String = "",
        @SerializedName("mobile") val mobile: String = "",
        @SerializedName("empId") val empId: Int = 0,
        @SerializedName("_permissionList") val _permissionList: String = "",
        @SerializedName("token") val token: String = "",
        @SerializedName("error") val error: String = ""
)

