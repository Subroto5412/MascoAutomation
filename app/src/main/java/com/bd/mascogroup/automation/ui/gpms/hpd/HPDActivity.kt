package com.bd.mascogroup.automation.ui.gpms.hpd

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.model.domainModel.BuyerWiseCardData
import com.bd.mascogroup.automation.data.model.domainModel.HourWiseCardData
import com.bd.mascogroup.automation.databinding.ActivityGarmentsProductionManagementBinding
import com.bd.mascogroup.automation.databinding.ActivityHourlyProductionDataBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.gpms.GPMSActivity
import com.bd.mascogroup.automation.ui.gpms.GPMSViewModel
import com.bd.mascogroup.automation.ui.gpms.IGPMSNavigator
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.LeaveApprovalActivity
import kotlinx.android.synthetic.main.activity_garments_production_management.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import javax.inject.Inject


class HPDActivity : BaseActivity<ActivityHourlyProductionDataBinding, HPDViewModel>(), IHPDNavigator, HPDAdapter.LeaveSummaryAdapterListener {

    private lateinit var mActivityHourlyProductionDataBinding: ActivityHourlyProductionDataBinding

    @Inject
    lateinit var mHPDViewModel: HPDViewModel

    @Inject
    lateinit var mHPDAdapter:HPDAdapter

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_hourly_production_data

    override val viewModel: HPDViewModel
        get() {
            return mHPDViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityHourlyProductionDataBinding = viewDataBinding
        viewModel.navigator = this
        mHPDAdapter.setListener(this)
        viewModel.getHPD(this, 8,"2021-10-05")


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
            return Intent(context, HPDActivity::class.java)
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

    fun setUpHourWises() {
        mActivityHourlyProductionDataBinding.activityHourlyProductionDataListRv.itemAnimator = DefaultItemAnimator()
        mActivityHourlyProductionDataBinding.activityHourlyProductionDataListRv.adapter = mHPDAdapter
    }

    fun updatHourWisesList(hourWiseCardData: List<HourWiseCardData>?) {
        mHPDAdapter.clearItems()
        if (!hourWiseCardData.isNullOrEmpty()) {
            mHPDAdapter.addItem(hourWiseCardData)
        }
    }

    fun subscribeToLiveDataHourWises() {
        mHPDViewModel.getHPDLiveData().observe(this, Observer { t ->
            mHPDViewModel.addHPDItemToList(t)
            updatHourWisesList(t)
        })
    }
}