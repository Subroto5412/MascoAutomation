package com.bd.mascogroup.automation.ui.hr_info.leave_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceStatusCardData
import com.bd.mascogroup.automation.data.model.domainModel.LeaveSummaryCardData
import com.bd.mascogroup.automation.databinding.ActivityLeaveDetailsBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivity
import com.bd.mascogroup.automation.ui.hr_info.daily_attendance.DailyAttendanceAdapter
import com.bd.mascogroup.automation.ui.hr_info.daily_attendance.DailyAttendanceViewModel
import kotlinx.android.synthetic.main.activity_daily_attendance.*
import kotlinx.android.synthetic.main.activity_leave_details.*
import kotlinx.android.synthetic.main.layout_common_header.*
import javax.inject.Inject

class LeaveDetailsActivity : BaseActivity<ActivityLeaveDetailsBinding, LeaveDetailsViewModel>(), ILeaveDetailsNavigator, LeaveDetailsLeaveSummaryAdapter.LeaveSummaryAdapterListener {



    lateinit var mActivityLeaveDetailsBinding: ActivityLeaveDetailsBinding

    @Inject
    lateinit var mLeaveDetailsViewModel: LeaveDetailsViewModel

    @Inject
    lateinit var mLeaveDetailsLeaveSummaryAdapter: LeaveDetailsLeaveSummaryAdapter

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_leave_details

    override val viewModel: LeaveDetailsViewModel
        get() {
            return mLeaveDetailsViewModel
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLeaveDetailsBinding = viewDataBinding
        viewModel.navigator = this
        mLeaveDetailsLeaveSummaryAdapter.setListener(this)
        activity_title_tv.setText("Personal Leave Details")


        viewModel.leaveSummary(this)
        setUpLeaveSummary()
        subscribeToLiveDataLeaveSummary()

        val CurrenePosition = (mActivityLeaveDetailsBinding.leaveDetailsLeaveSummaryListParentRv.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        activity_leave_details_next_month_im.setOnClickListener {
            leave_details_leave_summary_list_parent_rv.scrollToPosition(CurrenePosition+4)

            activity_leave_details_back_month_im.isVisible = true
            activity_leave_details_next_month_im.isGone = true
        }

        activity_leave_details_back_month_im.setOnClickListener {
            leave_details_leave_summary_list_parent_rv.scrollToPosition(CurrenePosition + 1)

            activity_leave_details_back_month_im.isGone = true
            activity_leave_details_next_month_im.isVisible = true
        }

        layout_header_back_im.setOnClickListener {
            val intent = HRInfoActivity.newIntent(this@LeaveDetailsActivity)
            startActivity(intent)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LeaveDetailsActivity::class.java)
        }
    }


    fun setUpLeaveSummary() {
        mActivityLeaveDetailsBinding.leaveDetailsLeaveSummaryListParentRv.itemAnimator = DefaultItemAnimator()
        mActivityLeaveDetailsBinding.leaveDetailsLeaveSummaryListParentRv.adapter = mLeaveDetailsLeaveSummaryAdapter
    }

    fun updateLeaveSummaryList(leaveSummaryCardData: List<LeaveSummaryCardData>?) {
        mLeaveDetailsLeaveSummaryAdapter.clearItems()
        if (!leaveSummaryCardData.isNullOrEmpty()) {
            mLeaveDetailsLeaveSummaryAdapter.addItem(leaveSummaryCardData)
        }
    }

    fun subscribeToLiveDataLeaveSummary() {
        mLeaveDetailsViewModel.getleaveSummaryLiveData().observe(this, Observer { t ->
            mLeaveDetailsViewModel.addLeaveSummaryItemToList(t)
            updateLeaveSummaryList(t)
        })
    }
}