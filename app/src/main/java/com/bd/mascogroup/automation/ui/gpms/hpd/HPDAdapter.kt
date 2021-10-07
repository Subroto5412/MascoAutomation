package com.bd.mascogroup.automation.ui.gpms.hpd

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.HourWiseCardData
import com.bd.mascogroup.automation.data.model.domainModel.LeaveSummaryCardData
import com.bd.mascogroup.automation.databinding.LayoutHeaderBinding
import com.bd.mascogroup.automation.databinding.LayoutHourlyProductionDataRowBinding
import com.bd.mascogroup.automation.databinding.LayoutLeaveDetailLeaveSummaryItemlistRowBinding
import com.bd.mascogroup.automation.ui.base.BaseViewHolder
import com.bd.mascogroup.automation.ui.hr_info.leave.leave_details.LeaveDetailsLeaveSummaryItemViewModel
import javax.inject.Inject

class HPDAdapter @Inject constructor(private val context: Context, val mHourWiseCardData: ArrayList<HourWiseCardData>, val dataManager: IDataManager) : RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var mListener: LeaveSummaryAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val binding: LayoutHourlyProductionDataRowBinding =
                LayoutHourlyProductionDataRowBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        return HourWiseListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  mHourWiseCardData.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItem(hourWiseCardData: List<HourWiseCardData>?) {
        mHourWiseCardData.addAll(hourWiseCardData!!)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mHourWiseCardData.clear()
    }

    fun setListener(listener: LeaveSummaryAdapterListener) {
        mListener = listener
    }

    interface LeaveSummaryAdapterListener {}

    inner class HourWiseListViewHolder(binding: LayoutHourlyProductionDataRowBinding) : BaseViewHolder(binding.root), HPDItemViewModel.HPDItemViewModelListener {
        val mBinding: LayoutHourlyProductionDataRowBinding = binding
        private var mHPDItemViewModel: HPDItemViewModel? = null
        override fun onBind(position: Int) {
            val hourWiseCardData: HourWiseCardData = mHourWiseCardData[position]
            mHPDItemViewModel = HPDItemViewModel(hourWiseCardData, position, this,dataManager)
            mBinding.viewModel = mHPDItemViewModel
        }
    }
}