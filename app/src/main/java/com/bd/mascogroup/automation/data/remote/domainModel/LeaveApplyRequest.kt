package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class LeaveApplyRequest(
        @SerializedName("leave_type") var leave_type: Int = 0,
        @SerializedName("leave_days") var leave_days: Int = 0,
        @SerializedName("apply_from") var apply_from: String = "",
        @SerializedName("apply_to") var apply_to: String = ""
)
