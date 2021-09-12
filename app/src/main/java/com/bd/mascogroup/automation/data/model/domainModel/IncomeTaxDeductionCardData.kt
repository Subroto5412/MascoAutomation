package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.IncomeTaxDeductionResponse

class IncomeTaxDeductionCardData constructor(var incomeTaxDeduction: IncomeTaxDeductionResponse){

    val sl : String
        get() = incomeTaxDeduction.sl

    val month : String
        get() = incomeTaxDeduction.month

    val deductionAmount : Double
        get() = incomeTaxDeduction.deductionAmount
}