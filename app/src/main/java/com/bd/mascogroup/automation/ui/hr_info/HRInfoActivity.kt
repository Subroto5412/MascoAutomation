package com.bd.mascogroup.automation.ui.hr_info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityHrInfoBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info.daily_attendance.DailyAttendanceActivity
import com.bd.mascogroup.automation.ui.hr_info.income_tax.IncomeTaxDeductionActivity
import com.bd.mascogroup.automation.ui.hr_info.leave_details.LeaveDetailsActivity
import kotlinx.android.synthetic.main.activity_hr_info.*
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_header.layout_header_back_im
import javax.inject.Inject

class HRInfoActivity : BaseActivity<ActivityHrInfoBinding, HRInfoViewModel>(), IHRInfoNavigator {
    
    @Inject
    override lateinit var viewModel: HRInfoViewModel

    private var mActivityHrInfoBinding: ActivityHrInfoBinding? = null

    @Inject
    lateinit var mHRInfoViewModel: HRInfoViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_hr_info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityHrInfoBinding = viewDataBinding
        viewModel.navigator = this
        activity_title_tv.setText("")

        activity_hr_daily_attendance_cl.setOnClickListener {
            val intent = DailyAttendanceActivity.newIntent(this@HRInfoActivity)
            startActivity(intent)
        }

        activity_hr_leave_details_cl.setOnClickListener {
            val intent = LeaveDetailsActivity.newIntent(this@HRInfoActivity)
            startActivity(intent)
        }

        activity_hr_income_tax_cl.setOnClickListener {
            val intent = IncomeTaxDeductionActivity.newIntent(this@HRInfoActivity)
            startActivity(intent)
        }

        layout_header_back_im.setOnClickListener {
            val intent = HomeActivity.newIntent(this@HRInfoActivity)
            startActivity(intent)
        }
        layout_footer_home_im.setOnClickListener {
            val intent = HomeActivity.newIntent(this)
            startActivity(intent)
            finish()
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HRInfoActivity::class.java)
        }
    }
}