package com.bd.mascogroup.automation.data.remote.domainModel

import com.google.gson.annotations.SerializedName

data class LeaveSummaryResponse(
        @SerializedName("error") var error: String = "",
        @SerializedName("_LeaveHistoryformatList") var _LeaveHistoryformatList : List<LeaveSummaryDataResponse>
)
