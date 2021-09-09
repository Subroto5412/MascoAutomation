package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.FinancialYearDataResponse

class FinancialYearCardData constructor(var fyd: FinancialYearDataResponse){

    val finalYearNo : Int
        get() = fyd.finalYearNo

    val finalYearName : String
        get() = fyd.finalYearName

    val yearName : String
        get() = fyd.yearName
}