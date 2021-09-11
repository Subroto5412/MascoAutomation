package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.TaxYearDataResponse

class TaxYearDataCardData constructor(var fyd: TaxYearDataResponse){

    val taxYearNo : Int
        get() = fyd.taxYearNo

    val yearName : String
        get() = fyd.yearName
}