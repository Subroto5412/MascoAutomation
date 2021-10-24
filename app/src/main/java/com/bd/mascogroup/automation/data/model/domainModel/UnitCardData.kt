package com.bd.mascogroup.automation.data.model.domainModel


import com.bd.mascogroup.automation.data.remote.domainModel.listUnitName


class UnitCardData constructor(var unitlist: listUnitName){


    val unitNo : Int
        get() = unitlist.unitNo

    val unitEName : String
        get() = unitlist.unitEName
}