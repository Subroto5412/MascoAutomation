package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class AssetDataDetailsResponse(
        @SerializedName("error") var error: String = "",
        @SerializedName("assetDataDetails") var assetDataDetails: assetDataDetails
)

data class assetDataDetails(
        @SerializedName("assetNo") var assetNo: String = "",
        @SerializedName("assetName") var assetName: String = "",
        @SerializedName("unitName") var unitName: String = "",
        @SerializedName("purchaseDate") var purchaseDate: String = "",
        @SerializedName("purchaseValue") var purchaseValue: String = "",
        @SerializedName("assetEntryDate") var assetEntryDate: String = "",
)
