package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class BuyerWiseDataResponse(
        @SerializedName("error") var error: String="",
        @SerializedName("_listBuyerWiseData") var _listBuyerWiseData: List<ListBuyerWiseData>
)

data class ListBuyerWiseData(
        @SerializedName("sl") var sl: String = "",
        @SerializedName("buyerName") var buyerName: String="",
        @SerializedName("orderNo") var orderNo: String="",
        @SerializedName("styleNo") var styleNo: String="",
        @SerializedName("buyerId") var buyerId: Int=0,
        @SerializedName("styleId") var styleId: Int=0,
        @SerializedName("buyerReferenceId") var buyerReferenceId: Int=0,
        @SerializedName("orderQty") var orderQty: Int=0,
        @SerializedName("sewingQty") var sewingQty: Int=0,
        @SerializedName("balance") var balance: Int=0
)