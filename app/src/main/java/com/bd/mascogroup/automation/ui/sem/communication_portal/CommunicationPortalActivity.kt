package com.bd.mascogroup.automation.ui.sem.communication_portal

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityCommunicationPortalBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.utils.AppConstants
import kotlinx.android.synthetic.main.activity_atm.*
import kotlinx.android.synthetic.main.activity_atm.activity_atm_asset_basic_data_cl
import kotlinx.android.synthetic.main.activity_buyer_wise_production_data.*
import kotlinx.android.synthetic.main.activity_communication_portal.*
import kotlinx.android.synthetic.main.activity_sem.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import java.util.ArrayList
import java.util.HashMap
import javax.inject.Inject


class CommunicationPortalActivity : BaseActivity<ActivityCommunicationPortalBinding, CommunicationPortalViewModel>(), ICommunicationPortalNavigator {

    private var mActivityCommunicationPortalBinding: ActivityCommunicationPortalBinding? = null

    @Inject
    lateinit var mCommunicationPortalViewModel: CommunicationPortalViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_communication_portal

    override val viewModel: CommunicationPortalViewModel
        get() {
            return mCommunicationPortalViewModel
        }

     var EmpNames = ArrayList<String>()
     var EmpId = ArrayList<Int>()
     var EmpNo:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityCommunicationPortalBinding = viewDataBinding
        viewModel.navigator = this
       // viewModel.getUnitName(this,activity_communication_portal_unit_name_value_sp)
        viewModel.getSearchName(this@CommunicationPortalActivity, activity_communication_portal_date_name_value_actv, EmpNo)
        //  viewModel.accessToken()
        //  viewModel.buttonPermission(this, activity_hr_main_daily_attendance_cl, activity_hr_main_leave_details_cl, activity_hr_main_income_tax_cl)
        //   viewModel.getSearchName(this, layout_header_search_actv)
//        viewModel.getSearchName(this)


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

        EmpId.clear()
        EmpNames.clear()
        EmpId.add(0)
        EmpNames.add("Select Unit")
        activity_communication_portal_unit_name_value_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {

                (parent!!.getChildAt(0) as TextView).setTextColor(Color.WHITE)
                (parent!!.getChildAt(0) as TextView).setTextSize(13F)

                val map: HashMap<String, String> = AppConstants.HasUnitNameList.get(position)

                EmpNo = map.get("unitNo")!!.toInt()

            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CommunicationPortalActivity::class.java)
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