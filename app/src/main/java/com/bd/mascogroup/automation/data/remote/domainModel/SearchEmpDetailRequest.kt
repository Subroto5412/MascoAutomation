package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class SearchEmpDetailRequest(
        @SerializedName("emp_code") var emp_code: String = ""
)
