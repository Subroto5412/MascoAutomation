package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class AssetDataDetailsRequest(
        @SerializedName("qr_code") var qr_code: String = ""
)
