package com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.leave_approval_form

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityLeaveApprovalFormBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info_system.HrInfoSystemActivity
import com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.LeaveApprovalActivity
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import javax.inject.Inject

class LeaveApprovalFormActivity : BaseActivity<ActivityLeaveApprovalFormBinding, LeaveApprovalFormViewModel>(), ILeaveApprovalFormNavigator {

    private var mActivityLeaveApprovalFormBinding: ActivityLeaveApprovalFormBinding? = null

    @Inject
    lateinit var mLeaveApprovalFormViewModel: LeaveApprovalFormViewModel

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
        activity_title_tv.setText("HR Information Systems")

       /* layout_header_back_im.setOnClickListener {
            val intent = LeaveApprovalActivity.newIntent(this)
            startActivity(intent)
            finish()
        }*/
        layout_footer_home_im.setOnClickListener {
            val intent = HomeActivity.newIntent(this)
            startActivity(intent)
            finish()
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

}