package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class OtpResponse(
    @SerializedName("response") val response: String = "",
    @SerializedName("error") val error: String = "",
    @SerializedName("message") val message: String = "",
    @SerializedName("data") val data: String = "",
    @SerializedName("token") val token: String = "",
    @SerializedName("image_name") val image_name: String = "",
    @SerializedName("user_entry_id") val user_entry_id: String = "",
    @SerializedName("mobile") val mobile: String = "",
)
