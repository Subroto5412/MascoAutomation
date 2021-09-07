package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class OtpRequest(
    @SerializedName("empId") val empId: String = ""
)