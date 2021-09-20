package com.bd.mascogroup.automation.ui.hr_info.daily_attendance

import android.util.Log
import androidx.databinding.ObservableField
import com.bd.mascogroup.automation.R
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
    val backgroundColor: ObservableField<Int>
    val backgroundColorHover: ObservableField<Int>
    val punchInValue: ObservableField<String>
    val punchOutValue: ObservableField<String>
    val additionTimeValue: ObservableField<String>
    val textColor: ObservableField<Int>

    interface DailyAttendanceListItemViewModelListener {
    }

    init {
        mDailyAttendanceCardData = dailyAttendanceCardData
        mListener = listener
        mPosition = ObservableField(position)

        val parts = mDailyAttendanceCardData.punchDate.split("-")
        val punchDate = parts[0]
        val punchMonth = parts[1]

        punchIn = ObservableField(mDailyAttendanceCardData.fPunchIn)
        punchOut = ObservableField(mDailyAttendanceCardData.fPunchOut)
        FSts = ObservableField(mDailyAttendanceCardData.fSts)
        additionTime = ObservableField(mDailyAttendanceCardData.additionTime)
        date = ObservableField(punchDate)
        month = ObservableField(punchMonth)

        val sdf = SimpleDateFormat("dd-MMM")
        val currentDate = sdf.format(Date())
        
        backgroundColor =  if ((punchDate+"-"+punchMonth).equals(currentDate)) {
            ObservableField(R.drawable.layout_daily_attendance_row_bg)
        } else {
            ObservableField(R.drawable.layout_daily_attendance_row_no_bg_color)
        }

        backgroundColorHover =  if ((punchDate+"-"+punchMonth).equals(currentDate)) {
            ObservableField(R.drawable.layout_daily_attendance_row_month_bg_hover)
        } else {

            if (mDailyAttendanceCardData.fSts.equals("P")){
                ObservableField(R.drawable.layout_daily_attendance_row_month_bg)
            }else{
                ObservableField(R.drawable.layout_daily_attendance_row_month_reg_bg_hover)
            }
        }

        textColor =  if ((punchDate+"-"+punchMonth).equals(currentDate)) {
            ObservableField(R.color.text_color)
        } else {

            if (mDailyAttendanceCardData.fSts.equals("P")){
                ObservableField(R.color.text_color)
            }else{
                ObservableField(R.color.white)
            }
        }

        punchInValue =  if (mDailyAttendanceCardData.fPunchIn.equals("12:00:00 AM")) {
            ObservableField("0")
        } else {
            ObservableField(mDailyAttendanceCardData.fPunchIn)
        }

        punchOutValue =  if (mDailyAttendanceCardData.fPunchOut.equals("12:00:00 AM")) {
            ObservableField("0")
        } else {
            ObservableField(mDailyAttendanceCardData.fPunchOut)
        }

        additionTimeValue =  if (mDailyAttendanceCardData.additionTime.equals("06:00:00")) {
            ObservableField("0")
        } else {
            ObservableField(mDailyAttendanceCardData.additionTime)
        }
    }
}