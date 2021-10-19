package com.bd.mascogroup.automation.ui.sem.communication_portal

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.TextView
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityCommunicationPortalBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.utils.AppConstants
import kotlinx.android.synthetic.main.activity_atm.*
import kotlinx.android.synthetic.main.activity_buyer_wise_production_data.*
import kotlinx.android.synthetic.main.activity_communication_portal.*
import kotlinx.android.synthetic.main.activity_sem.*
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_header.layout_header_back_im
import java.util.*
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

     var UnitNo:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityCommunicationPortalBinding = viewDataBinding
        viewModel.navigator = this
        activity_title_tv.setText("Communication Portal")
        viewModel.deleteAllEmpName()
        viewModel.getUnitName(this, activity_communication_portal_unit_name_value_sp, activity_communication_portal_date_name_value_actv,acp_name_value_tv, acp_designation_value_tv,
                acp_section_value_tv, acp_email_value_tv, acp_mobile_personal_value_tv, acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)

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

                UnitNo = map.get("unitNo")!!.toInt()
                activity_communication_portal_date_name_value_actv.setText("")
                if (UnitNo==0){
                    viewModel.getSearchAllName(this@CommunicationPortalActivity, activity_communication_portal_date_name_value_actv,acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv,
                            acp_email_value_tv, acp_mobile_personal_value_tv, acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv,acp_pic_im)
                }else{
                    viewModel.getSearchName(this@CommunicationPortalActivity, activity_communication_portal_date_name_value_actv, UnitNo.toString(),acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv,
                            acp_email_value_tv, acp_mobile_personal_value_tv, acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)
                }
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