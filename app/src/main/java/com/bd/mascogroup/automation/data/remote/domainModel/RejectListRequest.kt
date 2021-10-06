package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class RejectListRequest(
        @SerializedName("ActionBy") var ActionBy: String = "",
        @SerializedName("leaveRejectList") var leaveRejectList: List<LeaveRejectData>
)

data class LeaveRejectData(
        @SerializedName("ApplyNo") var ApplyNo: Int = 0
)