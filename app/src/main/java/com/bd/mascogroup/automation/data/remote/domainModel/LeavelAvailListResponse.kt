package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class LeavelAvailListResponse(
    @SerializedName("leaveTypeNo") var leaveTypeNo: Int =0,
    @SerializedName("abbreviation") var abbreviation: String = "",
    @SerializedName("maxBalance") var maxBalance: Int = 0,
    @SerializedName("avail") var avail: Int = 0,
)
