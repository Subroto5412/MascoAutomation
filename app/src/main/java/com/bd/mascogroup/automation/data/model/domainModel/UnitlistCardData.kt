package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.model.db.Unitlist
import com.bd.mascogroup.automation.data.remote.domainModel.listUnitName

class UnitlistCardData constructor(var unitlist: listUnitName){

    /*val id : Int
        get() = unitlist.id*/

    val unitNo : Int
        get() = unitlist.unitNo

    val unitEName : String
        get() = unitlist.unitEName
}