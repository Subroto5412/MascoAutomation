package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class LeavePendingApprovalRequest (
        @SerializedName("recommPersNo") var recommPersNo: Int = 0,
        @SerializedName("responsiblePersNo") var responsiblePersNo: Int = 0
        )