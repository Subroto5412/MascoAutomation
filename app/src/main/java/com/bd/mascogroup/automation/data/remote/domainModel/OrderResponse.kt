package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class OrderResponse (
        @SerializedName("response") val response: String = "",
        @SerializedName("error") val error: String = "",
        @SerializedName("message") val message: String = "",
        @SerializedName("data") val data: List<OrderDataResponse>

) {

}