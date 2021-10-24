package com.bd.mascogroup.automation.ui.sem.communication_portal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.widget.AdapterView
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
import kotlinx.android.synthetic.main.layout_top_search_header.*
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

    var UnitNo: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityCommunicationPortalBinding = viewDataBinding
        viewModel.navigator = this
        activity_title_tv.setText("Communication Portal")
        viewModel.deleteAllEmpName()
        viewModel.deleteAllUnit()

        viewModel.getUnitName(this, acp_unit_name_value_actv, acp_name_value_actv, acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv, acp_email_value_tv, acp_mobile_personal_value_tv,
                acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)


        acp_name_value_actv.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()

            viewModel.getSearchCodeByName(this, selectedItem, acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv, acp_email_value_tv, acp_mobile_personal_value_tv,
                    acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)
        }

        acp_unit_name_value_actv.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val selectedItem = parent.getItemAtPosition(position).toString()
            viewModel.getUnitCodeByName(this, selectedItem, acp_name_value_actv, acp_unit_name_value_actv, acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv, acp_email_value_tv, acp_mobile_personal_value_tv,
                    acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)
        }



        acp_unit_name_value_actv.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if (s.toString().isNullOrEmpty()) {
                    AppConstants.unit = 0
                    viewModel.getOnlineUnitName(this@CommunicationPortalActivity, acp_unit_name_value_actv, acp_name_value_actv, acp_name_value_tv, acp_designation_value_tv, acp_section_value_tv, acp_email_value_tv, acp_mobile_personal_value_tv,
                            acp_mobile_office_value_tv, acp_office_ip_value_tv, acp_unit_value_tv, acp_pic_im)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
            }
        })

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
}