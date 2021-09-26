package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class LeaveApplyResponse(
        @SerializedName("response") var response: Boolean = false,
        @SerializedName("error") var error: String = ""
)
