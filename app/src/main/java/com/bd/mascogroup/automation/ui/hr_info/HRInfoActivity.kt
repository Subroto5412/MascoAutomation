package com.bd.mascogroup.automation.ui.hr_info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityHrInfoBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.gpms.bwpd.BWPDActivity
import com.bd.mascogroup.automation.ui.gpms.bwpd.BWPDActivityModule
import com.bd.mascogroup.automation.ui.gpms.hp_details.HPDetailsActivity
import com.bd.mascogroup.automation.ui.gpms.hpd.HPDActivity
import com.bd.mascogroup.automation.ui.gpms.lwp.LWPActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info.attendance.AttendanceActivity
import com.bd.mascogroup.automation.ui.hr_info.attendance.daily_attendance.DailyAttendanceActivity
import com.bd.mascogroup.automation.ui.hr_info.leave.LeaveActivity
import com.bd.mascogroup.automation.ui.hr_info.tax.income_tax.IncomeTaxDeductionActivity
import com.bd.mascogroup.automation.ui.hr_info.leave.leave_details.LeaveDetailsActivity
import com.bd.mascogroup.automation.ui.hr_info.tax.TaxActivity
import kotlinx.android.synthetic.main.activity_attendance.*
import kotlinx.android.synthetic.main.activity_attendance.activity_hr_daily_attendance_cl
import kotlinx.android.synthetic.main.activity_attendance.activity_hr_main_daily_attendance_cl
import kotlinx.android.synthetic.main.activity_hr_info.*
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_header.layout_header_back_im
import kotlinx.android.synthetic.main.layout_hr_body.*
import kotlinx.android.synthetic.main.layout_top_search_header.*
import javax.inject.Inject

class HRInfoActivity : BaseActivity<ActivityHrInfoBinding, HRInfoViewModel>(), IHRInfoNavigator {

    private var mActivityHrInfoBinding: ActivityHrInfoBinding? = null

    @Inject
    lateinit var mHRInfoViewModel: HRInfoViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_hr_info

    override val viewModel: HRInfoViewModel
        get() {
            return mHRInfoViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityHrInfoBinding = viewDataBinding
        viewModel.navigator = this
        viewModel.accessToken()
        viewModel.buttonPermission(this, activity_hr_main_daily_attendance_cl, activity_hr_main_leave_details_cl, activity_hr_main_income_tax_cl)
        viewModel.getSearchName(this, layout_header_search_actv)
//        viewModel.getSearchName(this)

        activity_hr_daily_attendance_cl.setOnClickListener {
            openAttendanceActivity()
        }

        activity_hr_leave_details_cl.setOnClickListener {
            openLeaveActivity()
        }

        activity_hr_income_tax_cl.setOnClickListener {
            openTaxActivity()
        }

        layout_header_back_im.setOnClickListener {
            val intent = HomeActivity.newIntent(this@HRInfoActivity)
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
            return Intent(context, HRInfoActivity::class.java)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            val intent = HomeActivity.newIntent(this)
            startActivity(intent)
            finish()

        }
        return super.onKeyDown(keyCode, event)
    }

    override fun openAttendanceActivity(){
        val intent = AttendanceActivity.newIntent(this@HRInfoActivity)
        startActivity(intent)
        finish()
    }

    override fun openLeaveActivity(){
        val intent = LeaveActivity.newIntent(this@HRInfoActivity)
        startActivity(intent)
        finish()
    }

    override fun openTaxActivity(){
        val intent = TaxActivity.newIntent(this@HRInfoActivity)
        startActivity(intent)
        finish()
    }

    override fun openLWPActivity(){
        val intent = LWPActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun openHPDActivity(){
        val intent = HPDActivity.newIntent(this)
        startActivity(intent)
        finish()
    }
    override fun openHPDetailsActivity(){
        val intent = HPDetailsActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun openBWPDActivity(){
        val intent = BWPDActivity.newIntent(this)
        startActivity(intent)
        finish()
    }


}