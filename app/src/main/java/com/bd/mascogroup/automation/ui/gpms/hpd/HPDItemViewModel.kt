package com.bd.mascogroup.automation.ui.gpms.hpd

import androidx.databinding.ObservableField
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.HourWiseCardData
import com.bd.mascogroup.automation.data.model.domainModel.LeaveApprovalListCardData


class HPDItemViewModel(
        hourWiseCardData: HourWiseCardData,
        position: Int,
        listener: HPDItemViewModelListener,
        dataManager: IDataManager
) {
    val mListener: HPDItemViewModelListener
    val mPosition: ObservableField<Int>
    private val mHourWiseCardData: HourWiseCardData
    val sl: ObservableField<String>
    val hour: ObservableField<String>
    val output: ObservableField<String>


    interface HPDItemViewModelListener {}

    init {
        mHourWiseCardData = hourWiseCardData
        mListener = listener
        mPosition = ObservableField(position)

        sl = ObservableField(mHourWiseCardData.sl)
        hour = ObservableField(mHourWiseCardData.hour)
        output = ObservableField(mHourWiseCardData.output.toString())
    }
}