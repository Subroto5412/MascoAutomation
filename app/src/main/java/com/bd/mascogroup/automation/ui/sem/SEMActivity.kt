package com.bd.mascogroup.automation.ui.sem

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivitySemBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.sem.communication_portal.CommunicationPortalActivity
import kotlinx.android.synthetic.main.activity_sem.*
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_header.layout_header_back_im
import javax.inject.Inject


class SEMActivity : BaseActivity<ActivitySemBinding, SEMViewModel>(), ISEMNavigator {

    private var mActivitySemBinding: ActivitySemBinding? = null

    @Inject
    lateinit var mSEMViewModel: SEMViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_sem

    override val viewModel: SEMViewModel
        get() {
            return mSEMViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivitySemBinding = viewDataBinding
        viewModel.navigator = this
        activity_title_tv.setText("Search Engine Management")
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

        activity_sem_communication_portal_cl.setOnClickListener {
            openCommunicationPortalActivity()
        }

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
            return Intent(context, SEMActivity::class.java)
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

    fun openCommunicationPortalActivity(){
        val intent = CommunicationPortalActivity.newIntent(this)
        startActivity(intent)
        finish()
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