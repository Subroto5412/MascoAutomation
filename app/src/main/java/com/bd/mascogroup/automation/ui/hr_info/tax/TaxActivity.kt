package com.bd.mascogroup.automation.ui.hr_info.tax

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityIncomeTaxBinding
import com.bd.mascogroup.automation.databinding.ActivityLeaveBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivity
import com.bd.mascogroup.automation.ui.hr_info.attendance.AttendanceActivity
import com.bd.mascogroup.automation.ui.hr_info.attendance.daily_attendance.DailyAttendanceActivity
import com.bd.mascogroup.automation.ui.hr_info.leave.ILeaveNavigation
import com.bd.mascogroup.automation.ui.hr_info.leave.LeaveViewModel
import com.bd.mascogroup.automation.ui.hr_info.tax.income_tax.IncomeTaxDeductionActivity
import kotlinx.android.synthetic.main.activity_attendance.*
import kotlinx.android.synthetic.main.activity_attendance.activity_hr_daily_attendance_cl
import kotlinx.android.synthetic.main.activity_income_tax.*
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_header.layout_header_back_im
import javax.inject.Inject

class TaxActivity : BaseActivity<ActivityIncomeTaxBinding, TaxViewModel>(), ITaxNavigation {

    private var mActivityIncomeTaxBinding: ActivityIncomeTaxBinding? = null

    @Inject
    lateinit var mTaxViewModel: TaxViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_income_tax

    override val viewModel: TaxViewModel
        get() {
            return mTaxViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityIncomeTaxBinding = viewDataBinding
        viewModel.navigator = this
        activity_title_tv.setText("Income Tax History")
        activity_hr_income_tax_cl.setOnClickListener {
            val intent = IncomeTaxDeductionActivity.newIntent(this)
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
            return Intent(context, TaxActivity::class.java)
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