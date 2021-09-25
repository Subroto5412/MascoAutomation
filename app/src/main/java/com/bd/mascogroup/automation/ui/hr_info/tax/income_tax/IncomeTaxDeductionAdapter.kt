package com.bd.mascogroup.automation.ui.hr_info.tax.income_tax

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.IncomeTaxDeductionCardData
import com.bd.mascogroup.automation.databinding.LayoutIcomeTaxDeductionItemlistRowBinding
import com.bd.mascogroup.automation.ui.base.BaseViewHolder
import javax.inject.Inject

class IncomeTaxDeductionAdapter @Inject constructor(private val context: Context, val mIncomeTaxDeductionCardData: ArrayList<IncomeTaxDeductionCardData>, val dataManager: IDataManager) : RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var mListener: IncomeTaxDeductionAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val binding: LayoutIcomeTaxDeductionItemlistRowBinding =
                LayoutIcomeTaxDeductionItemlistRowBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        return IncomeTaxDeductionListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  mIncomeTaxDeductionCardData.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItem(incomeTaxDeductionCardData: List<IncomeTaxDeductionCardData>?) {
        mIncomeTaxDeductionCardData.addAll(incomeTaxDeductionCardData!!)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mIncomeTaxDeductionCardData.clear()
    }

    fun setListener(listener: IncomeTaxDeductionAdapterListener) {
        mListener = listener
    }

    interface IncomeTaxDeductionAdapterListener {}

    inner class IncomeTaxDeductionListViewHolder(binding: LayoutIcomeTaxDeductionItemlistRowBinding) : BaseViewHolder(binding.root), IncomeTaxDeductionItemViewModel.IncomeTaxDeductionItemViewModelListener {
        val mBinding: LayoutIcomeTaxDeductionItemlistRowBinding = binding
        private var mIncomeTaxDeductionItemViewModel: IncomeTaxDeductionItemViewModel? = null
        override fun onBind(position: Int) {
            val incomeTaxDeductionCardData: IncomeTaxDeductionCardData = mIncomeTaxDeductionCardData[position]
            mIncomeTaxDeductionItemViewModel = IncomeTaxDeductionItemViewModel(incomeTaxDeductionCardData, position, this,dataManager)
            mBinding.viewModel = mIncomeTaxDeductionItemViewModel
        }
    }
}