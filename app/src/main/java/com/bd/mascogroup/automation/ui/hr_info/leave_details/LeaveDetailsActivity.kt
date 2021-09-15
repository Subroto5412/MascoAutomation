package com.bd.mascogroup.automation.ui.hr_info.leave_details

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.model.domainModel.AvailSummaryCardData
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceStatusCardData
import com.bd.mascogroup.automation.data.model.domainModel.LeaveSummaryCardData
import com.bd.mascogroup.automation.databinding.ActivityLeaveDetailsBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivity
import com.bd.mascogroup.automation.ui.hr_info.daily_attendance.DailyAttendanceAdapter
import com.bd.mascogroup.automation.ui.hr_info.daily_attendance.DailyAttendanceViewModel
import com.bd.mascogroup.automation.utils.AppConstants
import kotlinx.android.synthetic.main.activity_daily_attendance.*
import kotlinx.android.synthetic.main.activity_leave_details.*
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import java.util.HashMap
import javax.inject.Inject

class LeaveDetailsActivity : BaseActivity<ActivityLeaveDetailsBinding, LeaveDetailsViewModel>(), ILeaveDetailsNavigator, LeaveDetailsLeaveSummaryAdapter.LeaveSummaryAdapterListener, LeaveDetailsAvailSummaryAdapter.AvailSummaryAdapterListener {



    lateinit var mActivityLeaveDetailsBinding: ActivityLeaveDetailsBinding

    @Inject
    lateinit var mLeaveDetailsViewModel: LeaveDetailsViewModel

    @Inject
    lateinit var mLeaveDetailsLeaveSummaryAdapter: LeaveDetailsLeaveSummaryAdapter

    @Inject
    lateinit var mLeaveDetailsAvailSummaryAdapter: LeaveDetailsAvailSummaryAdapter

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_leave_details

    override val viewModel: LeaveDetailsViewModel
        get() {
            return mLeaveDetailsViewModel
        }

    var fYEarSpId:Int = 0
    var fYEarSpName:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLeaveDetailsBinding = viewDataBinding
        viewModel.navigator = this
        mLeaveDetailsLeaveSummaryAdapter.setListener(this)
        mLeaveDetailsAvailSummaryAdapter.setListener(this)
        activity_title_tv.setText("Personal Leave Details")

        viewModel.getFinancialYear(this,leave_details_fyear_spinner)

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
            finish()
        }

        layout_footer_home_im.setOnClickListener {
            val intent = HomeActivity.newIntent(this)
            startActivity(intent)
            finish()
        }

        activity_leave_details_avail_summary_next_im.setOnClickListener {

            avail_summary_horizontal_scoll.scrollTo(700,0)
            activity_leave_details_avail_summary_back_im.isVisible = true
            activity_leave_details_avail_summary_next_im.isGone = true
        }
        activity_leave_details_avail_summary_back_im.setOnClickListener {
            avail_summary_horizontal_scoll.scrollTo(-700,0)
            activity_leave_details_avail_summary_back_im.isGone = true
            activity_leave_details_avail_summary_next_im.isVisible = true
        }

        leave_details_fyear_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {

                (parent!!.getChildAt(0) as TextView).setTextColor(Color.WHITE)
                (parent!!.getChildAt(0) as TextView).setTextSize(13F)

                val map: HashMap<String, String> = AppConstants.HasYearForLeaveList.get(position)
               // if (!map.get("finalYearNo").isNullOrEmpty()){
                    fYEarSpId= map.get("finalYearNo")!!.toInt()
                    Log.e("----------","-----------"+fYEarSpId)

                    viewModel.leaveSummary(this@LeaveDetailsActivity,fYEarSpId)
                    setUpLeaveSummary()
                    subscribeToLiveDataLeaveSummary()

                    setUpAvailSummary()
                    subscribeToLiveDataAvailSummary()
                //}
            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LeaveDetailsActivity::class.java)
        }
    }

    override fun openHRScreen(){
        val intent = HRInfoActivity.newIntent(this@LeaveDetailsActivity)
        startActivity(intent)
        finish()
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

   fun setUpAvailSummary() {
        mActivityLeaveDetailsBinding.availSummaryListParentRv.itemAnimator = DefaultItemAnimator()
        mActivityLeaveDetailsBinding.availSummaryListParentRv.adapter = mLeaveDetailsAvailSummaryAdapter
    }

    fun updateAvailSummaryList(availSummaryCardData: List<AvailSummaryCardData>?) {
        mLeaveDetailsAvailSummaryAdapter.clearItems()
        if (!availSummaryCardData.isNullOrEmpty()) {
            mLeaveDetailsAvailSummaryAdapter.addItem(availSummaryCardData)
        }
    }

    fun subscribeToLiveDataAvailSummary() {
        mLeaveDetailsViewModel.getavailSummaryLiveData().observe(this, Observer { t ->
            mLeaveDetailsViewModel.addAvailSummaryItemToList(t)
            updateAvailSummaryList(t)
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val intent = HRInfoActivity.newIntent(this)
            startActivity(intent)
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }
}