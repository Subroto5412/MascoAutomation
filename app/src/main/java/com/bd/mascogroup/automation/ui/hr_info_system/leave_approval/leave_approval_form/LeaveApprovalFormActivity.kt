package com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.leave_approval_form

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.model.domainModel.LeaveApprovalListCardData
import com.bd.mascogroup.automation.databinding.ActivityLeaveApprovalFormBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.LeaveApprovalActivity
import kotlinx.android.synthetic.main.activity_leave_approval_form.*
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import javax.inject.Inject

class LeaveApprovalFormActivity : BaseActivity<ActivityLeaveApprovalFormBinding, LeaveApprovalFormViewModel>(), ILeaveApprovalFormNavigator, LeaveApprovalFormAdapter.LeaveApprovalFormAdapterListener {

    private lateinit var mActivityLeaveApprovalFormBinding: ActivityLeaveApprovalFormBinding

    @Inject
    lateinit var mLeaveApprovalFormViewModel: LeaveApprovalFormViewModel

    @Inject
    lateinit var mLeaveApprovalFormAdapter: LeaveApprovalFormAdapter

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_leave_approval_form

    override val viewModel: LeaveApprovalFormViewModel
        get() {
            return mLeaveApprovalFormViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLeaveApprovalFormBinding = viewDataBinding
        viewModel.navigator = this
        mLeaveApprovalFormAdapter.setListener(this)
        activity_title_tv.setText("Leave Approval")

        viewModel.deleteAllLeaveApprovalData(this)
        setUpLeaveApproval()
        subscribeToLiveDataLeaveApproval()

        activity_approve_mcv.setOnClickListener {
            viewModel.submitLeaveApproveData(this)
        }

        activity_reject_mcv.setOnClickListener {
            viewModel.submitLeaveRejectData(this)
        }

        layout_header_back_im.setOnClickListener {
            val intent = LeaveApprovalActivity.newIntent(this)
            startActivity(intent)
            finish()
        }
        layout_footer_home_im.setOnClickListener {
            val intent = HomeActivity.newIntent(this)
            startActivity(intent)
            finish()
        }

        checking_all_leave_ck.setOnClickListener {

            if (checking_all_leave_ck.isChecked==true){
                viewModel.getAllLeaveApprovals(this, "true")
            }else{
                viewModel.getAllLeaveApprovals(this, "false")
            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LeaveApprovalFormActivity::class.java)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            val intent = LeaveApprovalActivity.newIntent(this)
            startActivity(intent)
            finish()

        }
        return super.onKeyDown(keyCode, event)
    }

    fun setUpLeaveApproval() {
        mActivityLeaveApprovalFormBinding.activityLeaveApprovalListRv.itemAnimator = DefaultItemAnimator()
        mActivityLeaveApprovalFormBinding.activityLeaveApprovalListRv.adapter = mLeaveApprovalFormAdapter
    }

    fun updateLeaveApprovalList(leaveApprovalListCardData: List<LeaveApprovalListCardData>?) {
        mLeaveApprovalFormAdapter.clearItems()
        if (!leaveApprovalListCardData.isNullOrEmpty()) {
            mLeaveApprovalFormAdapter.addItem(leaveApprovalListCardData)
        }
    }

    fun subscribeToLiveDataLeaveApproval() {
        mLeaveApprovalFormViewModel.leaveApprovalListLiveData.observe(this, Observer { t ->
            mLeaveApprovalFormViewModel.addLeaveApprovalListItemToList(t)
            updateLeaveApprovalList(t)
        })
    }

    override fun onNext(empCode: String, ischeck: String) {
        viewModel.getUpdateOrderNoListData(this, empCode, ischeck)
    }
    override fun screenRefresh(){
        finish()
        val intent = LeaveApprovalActivity.newIntent(this)
        startActivity(intent)
    }
}