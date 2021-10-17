package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.model.db.Empname

class ListEmployeeNameCardData constructor(var listEmployee : Empname){

    val id : Int
        get() = listEmployee.id

    val emP_CODE : String
        get() = listEmployee.emP_CODE


    val emp_full : String
        get() = listEmployee.emp_full

    val unitNo : Int
        get() = listEmployee.unitNo

}