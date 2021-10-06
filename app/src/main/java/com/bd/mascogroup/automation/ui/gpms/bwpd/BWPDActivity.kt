package com.bd.mascogroup.automation.ui.gpms.bwpd

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.model.domainModel.BuyerWiseCardData
import com.bd.mascogroup.automation.data.model.domainModel.DailyAttendanceStatusCardData
import com.bd.mascogroup.automation.databinding.ActivityBuyerWiseProductionDataBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.gpms.GPMSActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info.leave.leave_details.LeaveDetailsLeaveSummaryAdapter
import com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.LeaveApprovalActivity
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import javax.inject.Inject

class BWPDActivity : BaseActivity<ActivityBuyerWiseProductionDataBinding, BWPDViewModel>(), IBWPDNavigator, BWPDAdapter.BWPDAdapterListener {

    private lateinit var mActivityBuyerWiseProductionDataBinding: ActivityBuyerWiseProductionDataBinding

    @Inject
    lateinit var mBWPDAdapter: BWPDAdapter

    @Inject
    lateinit var mBWPDViewModel: BWPDViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_buyer_wise_production_data

    override val viewModel: BWPDViewModel
        get() {
            return mBWPDViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityBuyerWiseProductionDataBinding = viewDataBinding
        viewModel.navigator = this
        mBWPDAdapter.setListener(this)

        viewModel.getBWPD(this, 8,"2021-10-05")
        setUpStatus()
        subscribeToLiveDataBuyerWiseProductionStatus()

        layout_header_back_im.setOnClickListener {
            val intent = GPMSActivity.newIntent(this)
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
            return Intent(context, BWPDActivity::class.java)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            val intent = GPMSActivity.newIntent(this)
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

    fun setUpStatus() {
        mActivityBuyerWiseProductionDataBinding.activityBuyerWiseProductionDataListRv.itemAnimator = DefaultItemAnimator()
        mActivityBuyerWiseProductionDataBinding.activityBuyerWiseProductionDataListRv.adapter = mBWPDAdapter
    }

    fun updatBuyerWiseProductionStatusList(buyerWiseCardData: List<BuyerWiseCardData>?) {
        mBWPDAdapter.clearItems()
        if (!buyerWiseCardData.isNullOrEmpty()) {
            mBWPDAdapter.addItem(buyerWiseCardData)
        }
    }

    fun subscribeToLiveDataBuyerWiseProductionStatus() {
        mBWPDViewModel.getBWPDLiveData().observe(this, Observer { t ->
            mBWPDViewModel.addBWPDItemToList(t)
            updatBuyerWiseProductionStatusList(t)
        })
    }
}