package com.bd.mascogroup.automation.ui.hr_info.attendance.daily_attendance


import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceCardData
import com.bd.mascogroup.automation.databinding.LayoutDailyAttendanceItemlistRowBinding
import com.bd.mascogroup.automation.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.layout_daily_attendance_itemlist_row.view.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class DailyAttendanceAdapter @Inject constructor(private val context: Context, val mDailyAttendanceCardData: ArrayList<DailyAttendanceCardData>, val dataManager: IDataManager) : RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var mListener: DailyAttendanceAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val binding: LayoutDailyAttendanceItemlistRowBinding =
                LayoutDailyAttendanceItemlistRowBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        return DailyAttendanceListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  mDailyAttendanceCardData.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItem(dailyAttendanceCardData: List<DailyAttendanceCardData>?) {
        mDailyAttendanceCardData.addAll(dailyAttendanceCardData!!)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mDailyAttendanceCardData.clear()
    }

    fun setListener(listener: DailyAttendanceAdapterListener) {
        mListener = listener
    }

    interface DailyAttendanceAdapterListener {}

    inner class DailyAttendanceListViewHolder(binding: LayoutDailyAttendanceItemlistRowBinding) : BaseViewHolder(binding.root), DailyAttendanceListItemViewModel.DailyAttendanceListItemViewModelListener {
        val mBinding: LayoutDailyAttendanceItemlistRowBinding = binding
        private var mDailyAttendanceListItemViewModel: DailyAttendanceListItemViewModel? = null
        override fun onBind(position: Int) {
            val dailyAttendanceCardData: DailyAttendanceCardData = mDailyAttendanceCardData[position]
            mDailyAttendanceListItemViewModel = DailyAttendanceListItemViewModel(dailyAttendanceCardData, position, this,dataManager)
            mBinding.viewModel = mDailyAttendanceListItemViewModel
        }
    }
}