package com.bd.mascogroup.automation.ui.gpms.lwp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.LeaveSummaryCardData
import com.bd.mascogroup.automation.databinding.LayoutLeaveDetailLeaveSummaryItemlistRowBinding
import com.bd.mascogroup.automation.ui.base.BaseViewHolder
import com.bd.mascogroup.automation.ui.hr_info.leave.leave_details.LeaveDetailsLeaveSummaryItemViewModel
import javax.inject.Inject

class LWPAdapter @Inject constructor(private val context: Context, val mLeaveSummaryCardData: ArrayList<LeaveSummaryCardData>, val dataManager: IDataManager) : RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var mListener: LeaveSummaryAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val binding: LayoutLeaveDetailLeaveSummaryItemlistRowBinding =
                LayoutLeaveDetailLeaveSummaryItemlistRowBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        return LeaveSummaryListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  mLeaveSummaryCardData.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItem(leaveSummaryCardData: List<LeaveSummaryCardData>?) {
        mLeaveSummaryCardData.addAll(leaveSummaryCardData!!)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mLeaveSummaryCardData.clear()
    }

    fun setListener(listener: LeaveSummaryAdapterListener) {
        mListener = listener
    }

    interface LeaveSummaryAdapterListener {}

    inner class LeaveSummaryListViewHolder(binding: LayoutLeaveDetailLeaveSummaryItemlistRowBinding) : BaseViewHolder(binding.root), LeaveDetailsLeaveSummaryItemViewModel.leaveSummaryListItemViewModelListener {
        val mBinding: LayoutLeaveDetailLeaveSummaryItemlistRowBinding = binding
        private var mLeaveDetailsLeaveSummaryItemViewModel: LeaveDetailsLeaveSummaryItemViewModel? = null
        override fun onBind(position: Int) {
            val leaveSummaryCardData: LeaveSummaryCardData = mLeaveSummaryCardData[position]
            mLeaveDetailsLeaveSummaryItemViewModel = LeaveDetailsLeaveSummaryItemViewModel(leaveSummaryCardData, position, this,dataManager)
            mBinding.viewModel = mLeaveDetailsLeaveSummaryItemViewModel
        }
    }
}