package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.LeavelAvailListResponse

class LeavelAvailListCardData constructor(var leavelAvailListResponse: LeavelAvailListResponse) {

    val leaveTypeNo : Int
        get() = leavelAvailListResponse.leaveTypeNo

    val abbreviation : String
        get() = leavelAvailListResponse.abbreviation

    val maxBalance : Int
        get() = leavelAvailListResponse.maxBalance

    val avail : Int
        get() = leavelAvailListResponse.avail
}