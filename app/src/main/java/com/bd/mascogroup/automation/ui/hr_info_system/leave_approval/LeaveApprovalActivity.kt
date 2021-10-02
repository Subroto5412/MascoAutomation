package com.bd.mascogroup.automation.ui.hr_info_system.leave_approval

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityLeaveApprovalBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info_system.HrInfoSystemActivity
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_header.layout_header_back_im
import javax.inject.Inject

class LeaveApprovalActivity : BaseActivity<ActivityLeaveApprovalBinding, LeaveApprovalViewModel>(), ILeaveApprovalNavigator {

    private var mActivityLeaveApprovalBinding: ActivityLeaveApprovalBinding? = null

    @Inject
    lateinit var mLeaveApprovalViewModel: LeaveApprovalViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_leave_approval

    override val viewModel: LeaveApprovalViewModel
        get() {
            return mLeaveApprovalViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLeaveApprovalBinding = viewDataBinding
        viewModel.navigator = this
        activity_title_tv.setText("HR Information Systems")
        //    viewModel.accessToken()
        //   viewModel.buttonPermission(this, activity_hr_main_daily_attendance_cl, activity_hr_main_leave_details_cl, activity_hr_main_income_tax_cl)
        //      viewModel.getSearchName(this, layout_header_search_actv)
//        viewModel.getSearchName(this)

        /* activity_hr_daily_attendance_cl.setOnClickListener {
             openAttendanceActivity()
         }*/

        layout_header_back_im.setOnClickListener {
            val intent = HrInfoSystemActivity.newIntent(this)
            startActivity(intent)
            finish()
        }
        layout_footer_home_im.setOnClickListener {
            val intent = HomeActivity.newIntent(this)
            startActivity(intent)
            finish()
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LeaveApprovalActivity::class.java)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            val intent = HrInfoSystemActivity.newIntent(this)
            startActivity(intent)
            finish()

        }
        return super.onKeyDown(keyCode, event)
    }

    /*override fun openAttendanceActivity(){
        val intent = AttendanceActivity.newIntent(this)
        startActivity(intent)
        finish()
    }*/

}