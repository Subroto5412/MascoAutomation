package com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.leave_approval_form

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.model.domainModel.LeaveApprovalListCardData
import com.bd.mascogroup.automation.databinding.LayoutLeaveApprovalItemlistRowBinding
import com.bd.mascogroup.automation.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_leave_approval_form.view.*
import kotlinx.android.synthetic.main.layout_leave_approval_itemlist_row.view.*
import javax.inject.Inject

class LeaveApprovalFormAdapter @Inject constructor(private val context: Context, val mLeaveApprovalListCardData: ArrayList<LeaveApprovalListCardData>, val dataManager: IDataManager) : RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var mListener: LeaveApprovalFormAdapterListener
    var PRIVATE_MODE = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val binding: LayoutLeaveApprovalItemlistRowBinding =
                LayoutLeaveApprovalItemlistRowBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        return LeaveApprovalFormViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  mLeaveApprovalListCardData.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItem(leaveApprovalListCardData: List<LeaveApprovalListCardData>?) {
        mLeaveApprovalListCardData.addAll(leaveApprovalListCardData!!)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mLeaveApprovalListCardData.clear()
    }

    fun setListener(listener: LeaveApprovalFormAdapterListener) {
        mListener = listener
    }

    interface LeaveApprovalFormAdapterListener {
        fun onNext(empCode:String,ischeck:String)
    }

    inner class LeaveApprovalFormViewHolder(binding: LayoutLeaveApprovalItemlistRowBinding) : BaseViewHolder(binding.root), LeaveApprovalFormItemViewModel.leaveApprovalFormItemViewModelListener {
        val mBinding: LayoutLeaveApprovalItemlistRowBinding = binding
        private var mLeaveApprovalFormItemViewModel: LeaveApprovalFormItemViewModel? = null
        override fun onBind(position: Int) {
            val statusCheck = itemView.checking_leave_ck
            val leaveApprovalListCardData: LeaveApprovalListCardData = mLeaveApprovalListCardData[position]
            mLeaveApprovalFormItemViewModel = LeaveApprovalFormItemViewModel(leaveApprovalListCardData, position, this,dataManager, context)
            mBinding.viewModel = mLeaveApprovalFormItemViewModel

            val sharedPref: SharedPreferences = context.getSharedPreferences(leaveApprovalListCardData.emP_CODE, PRIVATE_MODE)

            statusCheck.setOnClickListener {
                if (statusCheck.isChecked==true){
                    mListener.onNext(leaveApprovalListCardData.emP_CODE, "true")
                }else{
                    mListener.onNext(leaveApprovalListCardData.emP_CODE,"false")
                }
            }
        }
    }
}