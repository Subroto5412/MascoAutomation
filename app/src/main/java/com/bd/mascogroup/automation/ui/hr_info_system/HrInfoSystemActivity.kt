package com.bd.mascogroup.automation.ui.hr_info_system

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityHrInfoSystemBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info.HRInfoViewModel
import com.bd.mascogroup.automation.ui.hr_info.attendance.AttendanceActivity
import com.bd.mascogroup.automation.ui.hr_info.leave.LeaveActivity
import com.bd.mascogroup.automation.ui.hr_info.tax.TaxActivity
import kotlinx.android.synthetic.main.activity_attendance.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_hr_body.*
import javax.inject.Inject

class HrInfoSystemActivity : BaseActivity<ActivityHrInfoSystemBinding, HrInfoSystemViewModel>(), IHrInfoSystemNavigator {

    private var mActivityHrInfoSystemBinding: ActivityHrInfoSystemBinding? = null

    @Inject
    lateinit var mHrInfoSystemViewModel: HrInfoSystemViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_hr_info_system

    override val viewModel: HrInfoSystemViewModel
        get() {
            return mHrInfoSystemViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityHrInfoSystemBinding = viewDataBinding
        viewModel.navigator = this
    //    viewModel.accessToken()
     //   viewModel.buttonPermission(this, activity_hr_main_daily_attendance_cl, activity_hr_main_leave_details_cl, activity_hr_main_income_tax_cl)
  //      viewModel.getSearchName(this, layout_header_search_actv)
//        viewModel.getSearchName(this)

       /* activity_hr_daily_attendance_cl.setOnClickListener {
            openAttendanceActivity()
        }*/

        layout_header_back_im.setOnClickListener {
            val intent = HomeActivity.newIntent(this)
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
            return Intent(context, HrInfoSystemActivity::class.java)
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

    /*override fun openAttendanceActivity(){
        val intent = AttendanceActivity.newIntent(this)
        startActivity(intent)
        finish()
    }*/

}