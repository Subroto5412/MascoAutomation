package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class LeavelListResponse(
    @SerializedName("error") var error: String = "",
    @SerializedName("_leaveAvailList") var _leaveAvailList: List<LeavelAvailListResponse>,
    @SerializedName("emp_details") var emp_details: LeavelListEmpDetailResponse
)
