package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class AvailSummaryRequest(
        @SerializedName("finalYear") var finalYear: Int = 0
)
