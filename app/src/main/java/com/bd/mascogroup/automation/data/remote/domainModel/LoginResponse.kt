package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
        @SerializedName("empCode") val empCode: String = "",
        @SerializedName("mobile") val mobile: String = "",
        @SerializedName("empId") val empId: Int = 0,
        @SerializedName("_permissionList") val _permissionList: List<_permissionList>,
        @SerializedName("token") val token: String = "",
        @SerializedName("error") val error: String = ""
)

data class _permissionList(
        @SerializedName("moduleName") val moduleName: String = "",
        @SerializedName("_subMenuList") val _subMenuList: List<_subMenuList>
)

data class _subMenuList(
        @SerializedName("activityName") val activityName: String = "",
        @SerializedName("manuId") val manuId: Int = 0,
        @SerializedName("manuStepId") val manuStepId: Int = 0,
        @SerializedName("parantManuId") val parantManuId: Int = 0,
        @SerializedName("_specialPermission") val _specialPermission: String = ""
)
