package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

 class OrderDataResponse (
    @SerializedName("order_no") val order_no: String = "",
    @SerializedName("customername") val customername: String = "",
    @SerializedName("customerid") val customerid: String = "",
    @SerializedName("address") val address: String = "",
    @SerializedName("customermobile") val customermobile: String = "",
    @SerializedName("receivermobile") val receivermobile: String = "",
    @SerializedName("delivery_man_wise_order_distribution_id") val delivery_man_wise_order_distribution_id: String = "",
    @SerializedName("order_gen_id") val order_gen_id: String = "",
    @SerializedName("customer_delivery_date") val customer_delivery_date: String = "",
    @SerializedName("from_time") val from_time: String = "",
    @SerializedName("to_time") val to_time: String = "",
    @SerializedName("order_date") val order_date: String = "",
    @SerializedName("issue_invoice") val issue_invoice: String = "",
    @SerializedName("latitude") val latitude: String = "",
    @SerializedName("longitude") val longitude: String = "",
    @SerializedName("total_amount") val total_amount: String = "",
    @SerializedName("total_due") val total_due: String = ""
)