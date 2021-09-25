package com.bd.mascogroup.automation.ui.hr_info.leave.leave_details

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.AvailSummaryCardData
import com.bd.mascogroup.automation.databinding.LayoutLeaveDetailAvailSummaryHeaderItemlistRowBinding
import com.bd.mascogroup.automation.databinding.LayoutLeaveDetailAvailSummaryItemlistRowBinding
import com.bd.mascogroup.automation.ui.base.BaseViewHolder
import com.bd.mascogroup.automation.utils.AppConstants
import kotlinx.android.synthetic.main.layout_daily_attendance_itemlist_row.view.*
import kotlinx.android.synthetic.main.layout_daily_attendance_itemlist_row.view.daily_attendance_row_item_parent_cl
import kotlinx.android.synthetic.main.layout_leave_detail_avail_summary_itemlist_row.view.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class LeaveDetailsAvailSummaryAdapter @Inject constructor(private val context: Context, val mAvailSummaryCardData: ArrayList<AvailSummaryCardData>, val dataManager: IDataManager) : RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var mListener: AvailSummaryAdapterListener

    private val VIEW_TYPE_EMPTY = AppConstants.Number.ZERO.value
    private val VIEW_TYPE_NORMAL = AppConstants.Number.TWO.value
    private val VIEW_TYPE_HEADER = AppConstants.Number.ONE.value

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {

            VIEW_TYPE_HEADER -> {
                val binding: LayoutLeaveDetailAvailSummaryHeaderItemlistRowBinding =
                        LayoutLeaveDetailAvailSummaryHeaderItemlistRowBinding.inflate(
                                LayoutInflater.from(parent.context),
                                parent,
                                false
                        )
                return AvailSummaryHeaderViewHolder(binding)
            }
            else -> {
                val binding: LayoutLeaveDetailAvailSummaryItemlistRowBinding =
                        LayoutLeaveDetailAvailSummaryItemlistRowBinding.inflate(
                                LayoutInflater.from(parent.context),
                                parent,
                                false
                        )
                return AvailSummaryListViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (mAvailSummaryCardData.size == AppConstants.Number.ZERO.value) {
            AppConstants.Number.ONE.value
        } else {
            mAvailSummaryCardData.size + 1
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemViewType(position: Int): Int {
       if (position == 0) {
            return VIEW_TYPE_HEADER
        } else {
            return VIEW_TYPE_NORMAL
        }
    }

    fun addItem(availSummaryCardData: List<AvailSummaryCardData>?) {
        mAvailSummaryCardData.addAll(availSummaryCardData!!)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mAvailSummaryCardData.clear()
    }

    fun setListener(listener: AvailSummaryAdapterListener) {
        mListener = listener
    }

    interface AvailSummaryAdapterListener {}

    inner class AvailSummaryListViewHolder(binding: LayoutLeaveDetailAvailSummaryItemlistRowBinding) : BaseViewHolder(binding.root), LeaveDetailsAvailSummaryItemViewModel.availSummaryListItemViewModelListener {
        val mBinding: LayoutLeaveDetailAvailSummaryItemlistRowBinding = binding
        private var mLeaveDetailsAvailSummaryItemViewModel: LeaveDetailsAvailSummaryItemViewModel? = null
        override fun onBind(position: Int) {
            val availSummaryCardData: AvailSummaryCardData = mAvailSummaryCardData[position-1]

            val avail_summary_sl_tv = itemView.avail_summary_sl_tv
            val avail_summary_leave_type_tv = itemView.avail_summary_leave_type_tv
            val avail_summary_avail_day_tv = itemView.avail_summary_avail_day_tv
            val avail_summary_from_date_tv = itemView.avail_summary_from_date_tv
            val avail_summary_to_date_tv = itemView.avail_summary_to_date_tv
            val avail_summary_application_date_tv = itemView.avail_summary_application_date_tv

            mLeaveDetailsAvailSummaryItemViewModel = LeaveDetailsAvailSummaryItemViewModel(availSummaryCardData, position, this,dataManager)
            mBinding.viewModel = mLeaveDetailsAvailSummaryItemViewModel

            if (availSummaryCardData.leaveType.equals("CL")){
                avail_summary_sl_tv.setBackgroundResource(R.color.cl_text_color)
                avail_summary_leave_type_tv.setBackgroundResource(R.color.cl_text_color)
                avail_summary_avail_day_tv.setBackgroundResource(R.color.cl_text_color)
                avail_summary_from_date_tv.setBackgroundResource(R.color.cl_text_color)
                avail_summary_to_date_tv.setBackgroundResource(R.color.cl_text_color)
                avail_summary_application_date_tv.setBackgroundResource(R.color.cl_text_color)
            }

            if (availSummaryCardData.leaveType.equals("EL")){
                avail_summary_sl_tv.setBackgroundResource(R.color.el_text_color)
                avail_summary_leave_type_tv.setBackgroundResource(R.color.el_text_color)
                avail_summary_avail_day_tv.setBackgroundResource(R.color.el_text_color)
                avail_summary_from_date_tv.setBackgroundResource(R.color.el_text_color)
                avail_summary_to_date_tv.setBackgroundResource(R.color.el_text_color)
                avail_summary_application_date_tv.setBackgroundResource(R.color.el_text_color)
            }

            if (availSummaryCardData.leaveType.equals("SL")){
                avail_summary_sl_tv.setBackgroundResource(R.color.sl_text_color)
                avail_summary_leave_type_tv.setBackgroundResource(R.color.sl_text_color)
                avail_summary_avail_day_tv.setBackgroundResource(R.color.sl_text_color)
                avail_summary_from_date_tv.setBackgroundResource(R.color.sl_text_color)
                avail_summary_to_date_tv.setBackgroundResource(R.color.sl_text_color)
                avail_summary_application_date_tv.setBackgroundResource(R.color.sl_text_color)
            }
        }
    }

    inner class AvailSummaryHeaderViewHolder(binding: LayoutLeaveDetailAvailSummaryHeaderItemlistRowBinding) :
            BaseViewHolder(binding.root) {
        private val mBinding: LayoutLeaveDetailAvailSummaryHeaderItemlistRowBinding = binding
        private lateinit var mLeaveDetailsAvailSummaryHeaderItemViewModel: LeaveDetailsAvailSummaryHeaderItemViewModel
        override fun onBind(position: Int) {
            mLeaveDetailsAvailSummaryHeaderItemViewModel = LeaveDetailsAvailSummaryHeaderItemViewModel()
            mBinding.viewModel = mLeaveDetailsAvailSummaryHeaderItemViewModel
            mBinding.executePendingBindings()
        }
    }
}