package com.bd.mascogroup.automation.data.model.domainModel

import com.bd.mascogroup.automation.data.remote.domainModel.AttendanceHistoryResponseList

class AttendanceHistoryResponseCardData constructor(val dailyAttendance : AttendanceHistoryResponseList) {

    val punchDate: String
        get() = dailyAttendance.punchDate

    val shiftIn: String
        get() = dailyAttendance.shiftIn

    val shiftOut: String
        get() = dailyAttendance.shiftOut

    val shiftLate: String
        get() = dailyAttendance.shiftLate

    val fPunchIn: String
        get() = dailyAttendance.fPunchIn

    val fPunchOut: String
        get() = dailyAttendance.fPunchOut

    val shiftName: String
        get() = dailyAttendance.shiftName

    val lateTime: String
        get() = dailyAttendance.lateTime

    val additionTime: String
        get() = dailyAttendance.additionTime

    val fSts: String
        get() = dailyAttendance.fSts
}