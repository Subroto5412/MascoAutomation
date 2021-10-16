package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class SearchEmpNameRequest(
        @SerializedName("emp_name") var emp_name: String = "",
        @SerializedName("unit_no") var unit_no: Int = 0
)
