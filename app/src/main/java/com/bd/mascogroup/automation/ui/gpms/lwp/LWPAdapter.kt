package com.bd.mascogroup.automation.ui.gpms.lwp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.LeaveSummaryCardData
import com.bd.mascogroup.automation.data.model.domainModel.LineWiseCardData
import com.bd.mascogroup.automation.databinding.LayoutLeaveDetailLeaveSummaryItemlistRowBinding
import com.bd.mascogroup.automation.databinding.LayoutLineWiseProductionRowBinding
import com.bd.mascogroup.automation.ui.base.BaseViewHolder
import com.bd.mascogroup.automation.ui.hr_info.leave.leave_details.LeaveDetailsLeaveSummaryItemViewModel
import javax.inject.Inject

class LWPAdapter @Inject constructor(private val context: Context, val mLineWiseCardData: ArrayList<LineWiseCardData>, val dataManager: IDataManager) : RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var mListener: LWPAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val binding: LayoutLineWiseProductionRowBinding =
                LayoutLineWiseProductionRowBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        return LineWiseListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  mLineWiseCardData.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItem(lineWiseCardData: List<LineWiseCardData>?) {
        mLineWiseCardData.addAll(lineWiseCardData!!)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mLineWiseCardData.clear()
    }

    fun setListener(listener: LWPAdapterListener) {
        mListener = listener
    }

    interface LWPAdapterListener {}

    inner class LineWiseListViewHolder(binding: LayoutLineWiseProductionRowBinding) : BaseViewHolder(binding.root), LWPItemViewModel.LWPItemViewModelListener {
        val mBinding: LayoutLineWiseProductionRowBinding = binding
        private var mLWPItemViewModel: LWPItemViewModel? = null
        override fun onBind(position: Int) {
            val lineWiseCardData: LineWiseCardData = mLineWiseCardData[position]
            mLWPItemViewModel = LWPItemViewModel(lineWiseCardData, position, this,dataManager)
            mBinding.viewModel = mLWPItemViewModel
        }
    }
}