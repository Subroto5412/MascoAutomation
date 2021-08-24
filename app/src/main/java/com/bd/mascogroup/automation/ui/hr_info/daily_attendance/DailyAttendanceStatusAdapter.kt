package com.bd.mascogroup.automation.ui.hr_info.daily_attendance

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceStatusCardData
import com.bd.mascogroup.automation.databinding.LayoutStatusItemlistRowBinding
import com.bd.mascogroup.automation.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.layout_daily_attendance_itemlist_row.view.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class DailyAttendanceStatusAdapter @Inject constructor(private val context: Context, val mDailyAttendanceStatusCardData: ArrayList<DailyAttendanceStatusCardData>, val dataManager: IDataManager) : RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var mListener: DailyAttendanceStatusAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val binding: LayoutStatusItemlistRowBinding =
            LayoutStatusItemlistRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return DailyAttendanceStatusListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  mDailyAttendanceStatusCardData.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }


    fun addItem(dailyAttendanceStatusCardData: List<DailyAttendanceStatusCardData>?) {
        mDailyAttendanceStatusCardData.addAll(dailyAttendanceStatusCardData!!)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mDailyAttendanceStatusCardData.clear()
    }

    fun setListener(listener: DailyAttendanceStatusAdapterListener) {
        mListener = listener
    }

    interface DailyAttendanceStatusAdapterListener {}

    inner class DailyAttendanceStatusListViewHolder(binding: LayoutStatusItemlistRowBinding) : BaseViewHolder(binding.root), DailyAttendanceStatusListItemViewModel.DailyAttendanceStatusListItemViewModelListener{
        val mBinding: LayoutStatusItemlistRowBinding = binding
        private var mDailyAttendanceStatusListItemViewModel: DailyAttendanceStatusListItemViewModel? = null
        override fun onBind(position: Int) {
            val dailyAttendanceStatusCardData: DailyAttendanceStatusCardData = mDailyAttendanceStatusCardData[position]
            mDailyAttendanceStatusListItemViewModel = DailyAttendanceStatusListItemViewModel(dailyAttendanceStatusCardData, position, this,dataManager)
            mBinding.viewModel = mDailyAttendanceStatusListItemViewModel
        }
    }
}