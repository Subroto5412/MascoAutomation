package com.bd.mascogroup.automation.ui.hr_info.daily_attendance

import androidx.databinding.ObservableField
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceCardData

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

    interface DailyAttendanceListItemViewModelListener {
    }

    init {
        mDailyAttendanceCardData = dailyAttendanceCardData
        mListener = listener
        mPosition = ObservableField(position)

        punchIn = ObservableField(mDailyAttendanceCardData.FPunchIn)
        punchOut = ObservableField(mDailyAttendanceCardData.FPunchOut)
        FSts = ObservableField(mDailyAttendanceCardData.FSts)
        additionTime = ObservableField(mDailyAttendanceCardData.additionTime)
        date = ObservableField(mDailyAttendanceCardData.PunchDate)
    }
}