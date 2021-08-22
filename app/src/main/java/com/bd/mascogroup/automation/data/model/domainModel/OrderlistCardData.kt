package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.model.db.Orderlist
import com.bd.mascogroup.automation.data.model.db.User

class OrderlistCardData constructor(val orderlist: Orderlist){
    val id: Int
        get() = orderlist.id

    val deliveryId: String
        get() = orderlist.deliveryId

    val invoice: String
        get() = orderlist.invoice

    val orderNo: String
        get() = orderlist.orderNo

    val orderGenId: String
        get() = orderlist.orderGenId

    val totalAmount: String
        get() = orderlist.totalAmount

    val totalDue: String
        get() = orderlist.totalDue

    val customerName: String
        get() = orderlist.customerName

    val customerId: String
        get() = orderlist.customerId

    val address: String
        get() = orderlist.address

    val fromTime: String
        get() = orderlist.fromTime

    val toTime: String
        get() = orderlist.toTime

    val orderDate: String
        get() = orderlist.orderDate

    val deliveryDate: String
        get() = orderlist.deliveryDate

    val customerMobile: String
        get() = orderlist.customerMobile

    val receiverMobile: String
        get() = orderlist.receiverMobile

    val late: String
        get() = orderlist.late

    val longe: String
        get() = orderlist.longe
}