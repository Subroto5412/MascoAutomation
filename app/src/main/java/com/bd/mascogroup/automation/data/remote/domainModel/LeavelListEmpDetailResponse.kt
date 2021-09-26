package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class LeavelListEmpDetailResponse (
    @SerializedName("emP_CODE") var emP_CODE: String = "",
    @SerializedName("emP_ENAME") var emP_ENAME: String = "",
    @SerializedName("doj") var doj: String = "",
    @SerializedName("deptEName") var deptEName: String = "",
    @SerializedName("sectEName") var sectEName: String = "",
    @SerializedName("desigEName") var desigEName: String = "",
    @SerializedName("unitEName") var unitEName: String = ""
        )