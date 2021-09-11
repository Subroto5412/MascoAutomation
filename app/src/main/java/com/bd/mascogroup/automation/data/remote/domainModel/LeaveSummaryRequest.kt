package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class LeaveSummaryRequest(
        @SerializedName("finalYear") var finalYear: Int = 0
)
