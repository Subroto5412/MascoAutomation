package com.bd.mascogroup.automation.ui.hr_info.leave

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityLeaveBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivity
import com.bd.mascogroup.automation.ui.hr_info.attendance.daily_attendance.DailyAttendanceActivity
import com.bd.mascogroup.automation.ui.hr_info.leave.apply.LeaveApplyActivity
import com.bd.mascogroup.automation.ui.hr_info.leave.leave_details.LeaveDetailsActivity
import kotlinx.android.synthetic.main.activity_attendance.*
import kotlinx.android.synthetic.main.activity_attendance.activity_hr_daily_attendance_cl
import kotlinx.android.synthetic.main.activity_leave.*
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_header.layout_header_back_im
import javax.inject.Inject

class LeaveActivity : BaseActivity<ActivityLeaveBinding, LeaveViewModel>(), ILeaveNavigation {

    private var mActivityLeaveBinding: ActivityLeaveBinding? = null

    @Inject
    lateinit var mLeaveViewModel: LeaveViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_leave

    override val viewModel: LeaveViewModel
        get() {
            return mLeaveViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLeaveBinding = viewDataBinding
        viewModel.navigator = this
        activity_title_tv.setText("Leave History")

        activity_hr_leave_details_cl.setOnClickListener {
            val intent = LeaveDetailsActivity.newIntent(this)
            startActivity(intent)
            finish()
        }

        activity_hr_leave_apply_cl.setOnClickListener {
            val intent = LeaveApplyActivity.newIntent(this)
            startActivity(intent)
            finish()
        }

        layout_header_back_im.setOnClickListener {
            val intent = HRInfoActivity.newIntent(this)
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
            return Intent(context, LeaveActivity::class.java)
        }
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