package com.bd.mascogroup.automation.ui.hr_info.income_tax

import android.util.Log
import androidx.databinding.ObservableField
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.IncomeTaxDeductionCardData
import java.text.SimpleDateFormat
import java.util.*

class IncomeTaxDeductionItemViewModel(
        incomeTaxDeductionCardData: IncomeTaxDeductionCardData,
        position: Int,
        listener: IncomeTaxDeductionItemViewModelListener,
        dataManager: IDataManager
) {
    val mListener: IncomeTaxDeductionItemViewModelListener
    val mPosition: ObservableField<Int>
    private val mIncomeTaxDeductionCardData: IncomeTaxDeductionCardData
    val sl: ObservableField<String>
    val month: ObservableField<String>
    val deductionAmount: ObservableField<String>
    val textColor: ObservableField<Int>
    val bgColor: ObservableField<Int>

    interface IncomeTaxDeductionItemViewModelListener {}

    init {
        mIncomeTaxDeductionCardData = incomeTaxDeductionCardData
        mListener = listener
        mPosition = ObservableField(position)

        sl = ObservableField(mIncomeTaxDeductionCardData.sl)
        month = ObservableField(mIncomeTaxDeductionCardData.month)
        deductionAmount = ObservableField(mIncomeTaxDeductionCardData.deductionAmount.toString())

        val sdf1 = SimpleDateFormat("MMMM")
        val currentDay = sdf1.format(Date())
        var month = monthForTax(currentDay.toString())

        textColor =  if (mIncomeTaxDeductionCardData.taxMonthNo.toInt()<month){
            ObservableField(R.color.radical_red)
        }else{
            ObservableField(R.color.text_color)
        }

        bgColor =  if (mIncomeTaxDeductionCardData.taxMonthNo.toInt()<month){
            ObservableField(R.color.radical_red)
        }else{
            ObservableField(R.color.white)
        }
    }
    fun monthForTax(monthName:String):Int{
        var month:Int = 0
        if(monthName.equals("January")){
            month = 7
        }else if(monthName.equals("February")){
            month = 8
        } else if(monthName.equals("March")){
            month = 9
        } else if(monthName.equals("April")){
            month = 10
        } else if(monthName.equals("May")){
            month = 11
        } else if(monthName.equals("June")){
            month = 12
        } else if(monthName.equals("July")){
            month = 1
        } else if(monthName.equals("August")){
            month = 2
        } else if(monthName.equals("September")){
            month = 3
        } else if(monthName.equals("October")){
            month = 4
        } else if(monthName.equals("November")){
            month = 5
        } else if(monthName.equals("December")){
            month = 6
        }
        return month
    }

}