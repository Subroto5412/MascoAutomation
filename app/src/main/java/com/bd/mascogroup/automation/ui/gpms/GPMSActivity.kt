package com.bd.mascogroup.automation.ui.gpms

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityGarmentsProductionManagementBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.gpms.bwpd.BWPDActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.LeaveApprovalActivity
import kotlinx.android.synthetic.main.activity_garments_production_management.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import javax.inject.Inject

class GPMSActivity : BaseActivity<ActivityGarmentsProductionManagementBinding, GPMSViewModel>(), IGPMSNavigator {

    private lateinit var mActivityGarmentsProductionManagementBinding: ActivityGarmentsProductionManagementBinding

    @Inject
    lateinit var mGPMSViewModel: GPMSViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_garments_production_management

    override val viewModel: GPMSViewModel
        get() {
            return mGPMSViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityGarmentsProductionManagementBinding = viewDataBinding
        viewModel.navigator = this

        activity_line_wise_production_cl.setOnClickListener {

        }

        activity_hourly_production_data_cl.setOnClickListener {

        }

        activitybuyer_wise_production_data_cl.setOnClickListener {
            openBuyerWiseProductionActivity()
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
            return Intent(context, GPMSActivity::class.java)
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

    fun openHrInfoSystemActivity(){
        val intent = LeaveApprovalActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    fun openBuyerWiseProductionActivity(){
        val intent = BWPDActivity.newIntent(this)
        startActivity(intent)
        finish()
    }
}