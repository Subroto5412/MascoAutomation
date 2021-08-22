package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

class LoginDataResponse (
        @SerializedName("delivery_man_data_id") val delivery_man_data_id: String = "",
        @SerializedName("name") val name: String = ""
)