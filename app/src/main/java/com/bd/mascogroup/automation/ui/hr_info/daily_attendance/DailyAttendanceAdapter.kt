package com.bd.mascogroup.automation.ui.hr_info.daily_attendance


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

    inner class DailyAttendanceListViewHolder(binding: LayoutDailyAttendanceItemlistRowBinding) : BaseViewHolder(binding.root), DailyAttendanceListItemViewModel.DailyAttendanceListItemViewModelListener{
        val mBinding: LayoutDailyAttendanceItemlistRowBinding = binding
        private var mDailyAttendanceListItemViewModel: DailyAttendanceListItemViewModel? = null
        override fun onBind(position: Int) {
            val dailyAttendanceCardData: DailyAttendanceCardData = mDailyAttendanceCardData[position]

            val dailyAttendanceCardView = itemView.daily_attendance_card_view
            mDailyAttendanceListItemViewModel = DailyAttendanceListItemViewModel(dailyAttendanceCardData, position, this,dataManager)
            mBinding.viewModel = mDailyAttendanceListItemViewModel

            val sdf = SimpleDateFormat("dd")
            val currentDate = sdf.format(Date())

            Log.e("---------------","---PunchDate-----Hello BD-------"+dailyAttendanceCardData.PunchDate)

            Log.e("---------------","--currentDate------Hello BD-------"+currentDate)
            if (dailyAttendanceCardData.PunchDate.equals(currentDate)){
                Log.e("---------------","--------Hello BD-------")
                dailyAttendanceCardView.setBackgroundResource(R.drawable.layout_daily_attendance_row_bg)
//                set(com.mis.mascobazar.R.drawable.edittext_round_red)
            }
        }
    }
}