package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.ListBuyerWiseData

class BuyerWiseCardData constructor(var buyerWiseData: ListBuyerWiseData){

    val sl : String
        get() = buyerWiseData.sl

    val buyerName : String
        get() = buyerWiseData.buyerName

    val orderNo : String
        get() = buyerWiseData.orderNo

    val styleNo : String
        get() = buyerWiseData.styleNo

    val buyerId : Int
        get() = buyerWiseData.buyerId

    val styleId : Int
        get() = buyerWiseData.styleId

    val buyerReferenceId : Int
        get() = buyerWiseData.buyerReferenceId

    val orderQty : Int
        get() = buyerWiseData.orderQty

    val sewingQty : Int
        get() = buyerWiseData.sewingQty

    val balance : Int
        get() = buyerWiseData.balance
}