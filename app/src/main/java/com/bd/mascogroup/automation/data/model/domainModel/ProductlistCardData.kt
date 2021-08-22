package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.model.db.Productlist

class ProductlistCardData constructor(val productlist: Productlist) {

    val id: Int
        get() = productlist.id

    val invoice: String
        get() = productlist.invoice

    val item: String
        get() = productlist.item

    val discount: String
        get() = productlist.discount

    val issue_qty: String
        get() = productlist.issue_qty

    val total_amount: String
        get() = productlist.total_amount

    val total_due: String
        get() = productlist.total_due
}