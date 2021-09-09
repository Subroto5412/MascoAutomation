package com.bd.mascogroup.automation.ui.hr_info.daily_attendance

import androidx.databinding.ObservableField
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceStatusCardData

class DailyAttendanceStatusListItemViewModel(
    dailyAttendanceStatusCardData: DailyAttendanceStatusCardData,
    position: Int,
    listener: DailyAttendanceStatusListItemViewModelListener,
    dataManager: IDataManager
) {
    val mListener: DailyAttendanceStatusListItemViewModelListener
    val mPosition: ObservableField<Int>
    private val mDailyAttendanceStatusCardData: DailyAttendanceStatusCardData
    val status: ObservableField<String>
    val statusValue: ObservableField<String>


    interface DailyAttendanceStatusListItemViewModelListener {
    }

    init {
        mDailyAttendanceStatusCardData = dailyAttendanceStatusCardData
        mListener = listener
        mPosition = ObservableField(position)

        status = ObservableField(mDailyAttendanceStatusCardData.status)
        statusValue = ObservableField(mDailyAttendanceStatusCardData.statusValue.toString())
    }
}