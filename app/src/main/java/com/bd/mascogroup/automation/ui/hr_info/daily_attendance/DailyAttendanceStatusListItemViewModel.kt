package com.bd.mascogroup.automation.ui.hr_info.daily_attendance

import androidx.databinding.ObservableField
import com.bd.mascogroup.automation.R
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
    val status_text_color: ObservableField<Int>


    interface DailyAttendanceStatusListItemViewModelListener {
    }

    init {
        mDailyAttendanceStatusCardData = dailyAttendanceStatusCardData
        mListener = listener
        mPosition = ObservableField(position)

        status = ObservableField(mDailyAttendanceStatusCardData.status)
        statusValue = ObservableField(mDailyAttendanceStatusCardData.statusValue.toString())

        status_text_color = if(mDailyAttendanceStatusCardData.status.equals("Late")){
            if (mDailyAttendanceStatusCardData.statusValue>2){
                ObservableField(R.color.radical_red)
            }else{
                ObservableField(R.color.white)
            }
        }else{
            ObservableField(R.color.white)
        }
    }
}