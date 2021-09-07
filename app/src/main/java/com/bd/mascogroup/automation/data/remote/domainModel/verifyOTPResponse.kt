package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class verifyOTPResponse(
    @SerializedName("OTP") val OTP: String = "",
    @SerializedName("empId") val empId: String = ""
)
