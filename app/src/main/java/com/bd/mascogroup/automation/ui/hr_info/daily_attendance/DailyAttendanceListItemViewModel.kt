package com.bd.mascogroup.automation.ui.hr_info.daily_attendance

import androidx.databinding.ObservableField
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceCardData
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DailyAttendanceListItemViewModel(
        dailyAttendanceCardData: DailyAttendanceCardData,
        position: Int,
        listener: DailyAttendanceListItemViewModelListener,
        dataManager: IDataManager
) {
    val mListener: DailyAttendanceListItemViewModelListener
    val mPosition: ObservableField<Int>
    private val mDailyAttendanceCardData: DailyAttendanceCardData
    val punchIn: ObservableField<String>
    val punchOut: ObservableField<String>
    val FSts: ObservableField<String>
    val additionTime: ObservableField<String>
    val date: ObservableField<String>
    val month: ObservableField<String>

    interface DailyAttendanceListItemViewModelListener {
    }

    init {
        mDailyAttendanceCardData = dailyAttendanceCardData
        mListener = listener
        mPosition = ObservableField(position)

        val parts = mDailyAttendanceCardData.punchDate.split("-")
        val punchDate = parts[0]
        val punchMonth = parts[1]

       /* var date = "2017-05-05 13:58:50 "
        val input = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val output = SimpleDateFormat("MMMM dd,yyyy @hh:mm:ss aa")*/

        punchIn = ObservableField(mDailyAttendanceCardData.fPunchIn)
        punchOut = ObservableField(mDailyAttendanceCardData.fPunchOut)
        FSts = ObservableField(mDailyAttendanceCardData.fSts)
        additionTime = ObservableField(mDailyAttendanceCardData.additionTime)
     //   var date2 = formatdate(mDailyAttendanceCardData.punchDate)
        date = ObservableField(punchDate)
        month = ObservableField(punchMonth)
    }

    fun formatdate(fdate: String) {
        var datetime: String = ""
        val inputFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy")
        val d = SimpleDateFormat("yyyy-MM-dd")
        try {
            val convertedDate: Date = inputFormat.parse(fdate)
            datetime = d.format(convertedDate)
        } catch (e: ParseException) {
        }
    }

}