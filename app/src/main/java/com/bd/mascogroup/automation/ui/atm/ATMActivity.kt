package com.bd.mascogroup.automation.ui.atm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityAtmBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import javax.inject.Inject

class ATMActivity : BaseActivity<ActivityAtmBinding, ATMViewModel>(), IATMNavigator {

    private var mActivityAtmBinding: ActivityAtmBinding? = null

    @Inject
    lateinit var mATMViewModel: ATMViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_atm

    override val viewModel: ATMViewModel
        get() {
            return mATMViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityAtmBinding = viewDataBinding
        viewModel.navigator = this
      //  viewModel.accessToken()
      //  viewModel.buttonPermission(this, activity_hr_main_daily_attendance_cl, activity_hr_main_leave_details_cl, activity_hr_main_income_tax_cl)
     //   viewModel.getSearchName(this, layout_header_search_actv)
//        viewModel.getSearchName(this)

      /*  activity_hr_daily_attendance_cl.setOnClickListener {
            openAttendanceActivity()
        }*/

       /* activity_hr_leave_details_cl.setOnClickListener {
            openLeaveActivity()
        }

        activity_hr_income_tax_cl.setOnClickListener {
            openTaxActivity()
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
            return Intent(context, ATMActivity::class.java)
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

   /* override fun openAttendanceActivity(){
        val intent = AttendanceActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun openLeaveActivity(){
        val intent = LeaveActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun openTaxActivity(){
        val intent = TaxActivity.newIntent(this)
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
    }*/


}