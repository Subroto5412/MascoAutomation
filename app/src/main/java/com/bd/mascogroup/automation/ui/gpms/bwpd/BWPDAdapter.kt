package com.bd.mascogroup.automation.ui.gpms.bwpd

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.BuyerWiseCardData
import com.bd.mascogroup.automation.databinding.LayoutBuyerWiseProductionDataRowBinding
import com.bd.mascogroup.automation.ui.base.BaseViewHolder
import javax.inject.Inject


class BWPDAdapter @Inject constructor(private val context: Context, val mBuyerWiseCardData: ArrayList<BuyerWiseCardData>, val dataManager: IDataManager) : RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var mListener: BWPDAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val binding: LayoutBuyerWiseProductionDataRowBinding =
                LayoutBuyerWiseProductionDataRowBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        return BWPDViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  mBuyerWiseCardData.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItem(buyerWiseCardData: List<BuyerWiseCardData>?) {
        mBuyerWiseCardData.addAll(buyerWiseCardData!!)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mBuyerWiseCardData.clear()
    }

    fun setListener(listener: BWPDAdapterListener) {
        mListener = listener
    }

    interface BWPDAdapterListener {}

    inner class BWPDViewHolder(binding: LayoutBuyerWiseProductionDataRowBinding) : BaseViewHolder(binding.root), BWPDItemViewModel.BWPDItemViewModelListener {
        val mBinding: LayoutBuyerWiseProductionDataRowBinding = binding
        private var mBWPDItemViewModel: BWPDItemViewModel? = null
        override fun onBind(position: Int) {
            val buyerWiseCardData: BuyerWiseCardData = mBuyerWiseCardData[position]
            mBWPDItemViewModel = BWPDItemViewModel(buyerWiseCardData, position, this,dataManager)
            mBinding.viewModel = mBWPDItemViewModel
        }
    }
}