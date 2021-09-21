package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.DailyAttendanceStatusResponse

class DailyAttendanceStatusCardData constructor(val DAS : DailyAttendanceStatusResponse){

    val status : String
        get() = DAS.status

    val statusValue : String
        get() =DAS.statusValue
}