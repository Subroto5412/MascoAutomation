package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.DailyAttendanceResponse

class DailyAttendanceCardData constructor(val dailyAttendance : DailyAttendanceResponse){

    val additionTime : String
        get() = dailyAttendance.additionTime

    val FPunchIn : String
        get() = dailyAttendance.FPunchIn

    val FPunchOut : String
        get() = dailyAttendance.FPunchOut

    val FSts : String
        get() = dailyAttendance.FSts

    val PunchDate : String
        get() = dailyAttendance.PunchDate

    val ShiftIn : String
        get() = dailyAttendance.ShiftIn

    val ShiftLate : String
        get() = dailyAttendance.ShiftLate

    val ShiftName : String
        get() = dailyAttendance.ShiftName

    val ShiftOut : String
        get() = dailyAttendance.ShiftOut
}