package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.listUnitName

class UnitCardData constructor(var unitName: listUnitName){
    val unitNo : Int
        get() = unitName.unitNo

    val unitEName : String
        get() = unitName.unitEName
}