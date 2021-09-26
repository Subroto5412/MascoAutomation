package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.LeavelListEmpDetailResponse

class LeavelListEmpDetailCardData constructor(var leavelListEmpDetailResponse: LeavelListEmpDetailResponse) {

    val desigEName : String
        get() = leavelListEmpDetailResponse.desigEName
}
