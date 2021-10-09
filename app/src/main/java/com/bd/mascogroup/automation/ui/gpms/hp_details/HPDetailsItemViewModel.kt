package com.bd.mascogroup.automation.ui.gpms.hp_details

import androidx.databinding.ObservableField
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.HourlyProductionDetailsCardData

class HPDetailsItemViewModel(
        hourlyProductionDetailsCardData: HourlyProductionDetailsCardData,
        position: Int,
        listener: HPDetailsItemViewModelListener,
        dataManager: IDataManager
) {
    val mListener: HPDetailsItemViewModelListener
    val mPosition: ObservableField<Int>
    private val mHourlyProductionDetailsCardData: HourlyProductionDetailsCardData
    val sl: ObservableField<String>
    val hour: ObservableField<String>
    val cutting: ObservableField<String>
    val lineInput: ObservableField<String>
    val sewingOutput: ObservableField<String>
    val iron: ObservableField<String>
    val folding: ObservableField<String>
    val ploy: ObservableField<String>
    val cartoon: ObservableField<String>



    interface HPDetailsItemViewModelListener {}

    init {
        mHourlyProductionDetailsCardData = hourlyProductionDetailsCardData
        mListener = listener
        mPosition = ObservableField(position)

        sl = ObservableField(mHourlyProductionDetailsCardData.sl)
        hour = ObservableField(mHourlyProductionDetailsCardData.hour)
        cutting = ObservableField(mHourlyProductionDetailsCardData.cutting.toString())
        lineInput = ObservableField(mHourlyProductionDetailsCardData.lineInput.toString())
        sewingOutput = ObservableField(mHourlyProductionDetailsCardData.sewingOutput.toString())
        iron = ObservableField(mHourlyProductionDetailsCardData.iron.toString())
        folding = ObservableField(mHourlyProductionDetailsCardData.folding.toString())
        ploy = ObservableField(mHourlyProductionDetailsCardData.ploy.toString())
        cartoon = ObservableField(mHourlyProductionDetailsCardData.cartoon.toString())

    }
}