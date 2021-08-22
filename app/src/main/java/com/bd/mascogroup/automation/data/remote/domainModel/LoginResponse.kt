package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
        @SerializedName("response") val response: String = "",
        @SerializedName("error") val error: String = "",
        @SerializedName("token") val errorType: String = "",
        @SerializedName("data") val data: LoginDataResponse,

        //	@Expose(deserialize = false) // deserialize is this filed is not required
        @SerializedName("message") val message: String = ""
)

