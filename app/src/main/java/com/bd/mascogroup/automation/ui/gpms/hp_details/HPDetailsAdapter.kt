package com.bd.mascogroup.automation.ui.gpms.hp_details

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.BuyerWiseCardData
import com.bd.mascogroup.automation.data.model.domainModel.HourlyProductionDetailsCardData
import com.bd.mascogroup.automation.databinding.*
import com.bd.mascogroup.automation.ui.base.BaseViewHolder
import com.bd.mascogroup.automation.ui.gpms.bwpd.BWPDItemViewModel
import com.bd.mascogroup.automation.ui.hr_info.leave.leave_details.LeaveDetailsAvailSummaryHeaderItemViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import javax.inject.Inject

class HPDetailsAdapter @Inject constructor(private val context: Context, val mHourlyProductionDetailsCardData: ArrayList<HourlyProductionDetailsCardData>, val dataManager: IDataManager) : RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var mListener: HPDetailsAdapterListener

    private val VIEW_TYPE_EMPTY = AppConstants.Number.ZERO.value
    private val VIEW_TYPE_NORMAL = AppConstants.Number.TWO.value
    private val VIEW_TYPE_HEADER = AppConstants.Number.ONE.value

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
       when (viewType) {

           VIEW_TYPE_HEADER -> {
               val binding: LayoutHourlyProductionDetailsHeaderRowBinding =
                       LayoutHourlyProductionDetailsHeaderRowBinding.inflate(
                               LayoutInflater.from(parent.context),
                               parent,
                               false
                       )
               return HourlyProductionDetailsHeaderViewHolder(binding)
           }
           else -> {
               val binding: LayoutHourlyProductionDetailsRowBinding =
                       LayoutHourlyProductionDetailsRowBinding.inflate(
                               LayoutInflater.from(parent.context),
                               parent,
                               false
                       )
               return HourlyProductionDetailsViewHolder(binding)
           }
       }
   }


   /* override fun getItemCount(): Int {
        return  mHourlyProductionDetailsCardData.size
    }*/

    override fun getItemCount(): Int {
        return if (mHourlyProductionDetailsCardData.size == AppConstants.Number.ZERO.value) {
            AppConstants.Number.ONE.value
        } else {
            mHourlyProductionDetailsCardData.size + 1
        }
    }


    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return VIEW_TYPE_HEADER
        } else {
            return VIEW_TYPE_NORMAL
        }
    }
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItem(hourlyProductionDetailsCardData: List<HourlyProductionDetailsCardData>?) {
        mHourlyProductionDetailsCardData.addAll(hourlyProductionDetailsCardData!!)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mHourlyProductionDetailsCardData.clear()
    }

    fun setListener(listener: HPDetailsAdapterListener) {
        mListener = listener
    }

    interface HPDetailsAdapterListener {}

    inner class HourlyProductionDetailsViewHolder(binding: LayoutHourlyProductionDetailsRowBinding) : BaseViewHolder(binding.root), HPDetailsItemViewModel.HPDetailsItemViewModelListener {
        val mBinding: LayoutHourlyProductionDetailsRowBinding = binding
        private var mHPDetailsItemViewModel: HPDetailsItemViewModel? = null
        override fun onBind(position: Int) {
            val hourlyProductionDetailsCardData: HourlyProductionDetailsCardData = mHourlyProductionDetailsCardData[position-1]
            mHPDetailsItemViewModel = HPDetailsItemViewModel(hourlyProductionDetailsCardData, position, this,dataManager)
            mBinding.viewModel = mHPDetailsItemViewModel
        }
    }

    inner class HourlyProductionDetailsHeaderViewHolder(binding: LayoutHourlyProductionDetailsHeaderRowBinding) :
            BaseViewHolder(binding.root) {
        private val mBinding: LayoutHourlyProductionDetailsHeaderRowBinding = binding
        private lateinit var mHPDetailsHeaderItemViewModel: HPDetailsHeaderItemViewModel
        override fun onBind(position: Int) {
            mHPDetailsHeaderItemViewModel = HPDetailsHeaderItemViewModel()
            mBinding.viewModel = mHPDetailsHeaderItemViewModel
            mBinding.executePendingBindings()
        }
    }
}