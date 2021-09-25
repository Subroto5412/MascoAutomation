package com.bd.mascogroup.automation.ui.hr_info.attendance.daily_attendance

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
    val statusTextColor: ObservableField<Int>

    interface DailyAttendanceListItemViewModelListener {
    }

    init {
        mDailyAttendanceCardData = dailyAttendanceCardData
        mListener = listener
        mPosition = ObservableField(position)

        val parts = mDailyAttendanceCardData.punchDate.split("-")
        val punchDate = parts[0]
        val punchMonth = parts[1]

        Log.e("------","--------punchDate-----"+mDailyAttendanceCardData.punchDate)

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

            if (mDailyAttendanceCardData.fSts.equals("WHD") || mDailyAttendanceCardData.fSts.equals("CHD") || mDailyAttendanceCardData.fSts.equals("GHD")){
                ObservableField(R.drawable.layout_daily_attendance_row_month_reg_bg_hover)
            }else{
                ObservableField(R.drawable.layout_daily_attendance_row_month_bg)
            }
        }

        textColor =  if ((punchDate+"-"+punchMonth).equals(currentDate)) {
            ObservableField(R.color.text_color)
        } else {

            if (mDailyAttendanceCardData.fSts.equals("WHD") || mDailyAttendanceCardData.fSts.equals("CHD") || mDailyAttendanceCardData.fSts.equals("GHD")){
                ObservableField(R.color.white)
            }else{
                ObservableField(R.color.text_color)
            }
        }

        punchInValue =  if (mDailyAttendanceCardData.fPunchIn.isNullOrEmpty()) {
            ObservableField("0")
        } else {
            ObservableField(mDailyAttendanceCardData.fPunchIn)
        }

        punchOutValue =  if (mDailyAttendanceCardData.fPunchOut.isNullOrEmpty()) {
            ObservableField("0")
        } else {
            ObservableField(mDailyAttendanceCardData.fPunchOut)
        }

        additionTimeValue =  if (mDailyAttendanceCardData.additionTime.isNullOrEmpty()) {
            ObservableField("0")
        } else {
            ObservableField(mDailyAttendanceCardData.additionTime)
        }

        statusTextColor = if(mDailyAttendanceCardData.fSts.equals("QO") || mDailyAttendanceCardData.fSts.equals("SL") || mDailyAttendanceCardData.fSts.equals("CL") || mDailyAttendanceCardData.fSts.equals("EL") || mDailyAttendanceCardData.fSts.equals("LWP")){
            ObservableField(R.color.radical_red)
        }else{
            ObservableField(R.color.white)
        }
    }
}