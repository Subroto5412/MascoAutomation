package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class LoginByUserIdRequest(
    @SerializedName("empCode") val empCode: String = ""
)
