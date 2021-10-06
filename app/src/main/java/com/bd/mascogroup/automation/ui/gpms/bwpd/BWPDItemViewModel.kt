package com.bd.mascogroup.automation.ui.gpms.bwpd

import androidx.databinding.ObservableField
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.BuyerWiseCardData
import com.bd.mascogroup.automation.data.model.domainModel.LeaveApprovalListCardData

class BWPDItemViewModel(
        buyerWiseCardData: BuyerWiseCardData,
        position: Int,
        listener: BWPDItemViewModelListener,
        dataManager: IDataManager
) {
    val mListener: BWPDItemViewModelListener
    val mPosition: ObservableField<Int>
    private val mBuyerWiseCardData: BuyerWiseCardData
    val sl: ObservableField<String>
    val buyerName: ObservableField<String>
    val styleNo: ObservableField<String>
    val orderNo: ObservableField<String>
    val orderQty: ObservableField<String>
    val sewingQty: ObservableField<String>
    val balance: ObservableField<String>


    interface BWPDItemViewModelListener {}

    init {
        mBuyerWiseCardData = buyerWiseCardData
        mListener = listener
        mPosition = ObservableField(position)

        sl = ObservableField(mBuyerWiseCardData.sl)
        buyerName = ObservableField(mBuyerWiseCardData.buyerName)
        styleNo = ObservableField(mBuyerWiseCardData.styleNo)
        orderNo = ObservableField(mBuyerWiseCardData.orderNo)
        orderQty = ObservableField(mBuyerWiseCardData.orderQty.toString())
        sewingQty = ObservableField(mBuyerWiseCardData.sewingQty.toString())
        balance = ObservableField(mBuyerWiseCardData.balance.toString())
    }
}