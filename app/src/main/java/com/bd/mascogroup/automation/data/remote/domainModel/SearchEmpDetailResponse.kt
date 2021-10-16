package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class SearchEmpDetailResponse(
        @SerializedName("emp_code") var emp_code: String = "",
        @SerializedName("empDetails") var empDetails: List<EmpDetails>
)

data class EmpDetails(
        @SerializedName("emP_CODE") var emP_CODE: String = "",
        @SerializedName("emP_ID") var emP_ID: Int = 0,
        @SerializedName("emP_ENAME") var emP_ENAME: String = "",
        @SerializedName("desigEName") var desigEName: String = "",
        @SerializedName("personal_mobile") var personal_mobile: String = "",
        @SerializedName("ip") var ip: String = "",
        @SerializedName("office_mobile") var office_mobile: String = "",
        @SerializedName("email") var email: String = "",
        @SerializedName("sectEName") var sectEName: String = "",
        @SerializedName("deptEName") var deptEName: String = "",
        @SerializedName("unitEName") var unitEName: String = "",
        @SerializedName("img_url") var img_url: String = "",
)
