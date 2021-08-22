package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

class ProductDataResponse(
        @SerializedName("issue_invoice") val issue_invoice: String = "",
        @SerializedName("item") val item: String = "",
        @SerializedName("discount") val discount: String = "",
        @SerializedName("issue_qty") val issue_qty: String = "",
        @SerializedName("total_amount") val total_amount: String = "",
        @SerializedName("total_due") val total_due: String = ""
)