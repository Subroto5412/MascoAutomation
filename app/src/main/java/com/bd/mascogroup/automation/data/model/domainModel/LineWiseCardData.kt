package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.LineWiseProduction


class LineWiseCardData constructor(var lineWise: LineWiseProduction){

    val goodGarments : Int
        get() = lineWise.goodGarments

    val lineName : String
        get() = lineWise.lineName
}