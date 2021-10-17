package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class SearchEmpNameResponse(
        @SerializedName("error") var error: String = "",
        @SerializedName("_listEmployee") var _listEmployee: List<listEmployee>
)


data class listEmployee(
        @SerializedName("emP_CODE") var emP_CODE: String = "",
        @SerializedName("emp_full") var emp_full: String = "",
        @SerializedName("unitNo") var unitNo: Int = 0
)