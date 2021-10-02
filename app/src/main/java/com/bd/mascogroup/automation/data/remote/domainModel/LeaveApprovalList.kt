package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class LeaveApprovalList(
        @SerializedName("error") var error: String = "",
        @SerializedName("_leavePendingList") var _leavePendingList: List<LeavePendingListData>
)
