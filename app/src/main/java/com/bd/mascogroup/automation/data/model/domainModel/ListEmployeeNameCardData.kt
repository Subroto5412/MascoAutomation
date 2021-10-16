package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.listEmployee

class ListEmployeeNameCardData constructor(var listEmployee : listEmployee){

    val emP_CODE : String
        get() = listEmployee.emP_CODE


    val emp_full : String
        get() = listEmployee.emp_full

}