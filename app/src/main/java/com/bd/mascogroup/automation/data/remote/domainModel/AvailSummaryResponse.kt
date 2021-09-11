package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class AvailSummaryResponse(
        @SerializedName("error") var error: String = "",
        @SerializedName("_availHistoryList") var _availHistoryList : List<AvailSummaryDataResponse>
)
