package com.bd.mascogroup.automation.ui.gpms.hpd

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
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
import com.bd.mascogroup.automation.utils.AppConstants
import kotlinx.android.synthetic.main.activity_buyer_wise_production_data.*
import kotlinx.android.synthetic.main.activity_buyer_wise_production_data.activity_buyer_wise_production_data_date_value_tv
import kotlinx.android.synthetic.main.activity_garments_production_management.*
import kotlinx.android.synthetic.main.activity_hourly_production_data.*
import kotlinx.android.synthetic.main.activity_line_wise_production.*
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_header.layout_header_back_im
import java.util.*
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
    var HPDSpNo:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityHourlyProductionDataBinding = viewDataBinding
        viewModel.navigator = this
        mHPDAdapter.setListener(this)
        activity_title_tv.setText("Hourly Production Data")

        activity_hourly_production_data_date_value_tv.setText(viewModel.getCurrentDate())
        viewModel.getUnitName(this, activity_hourly_production_data_unit_name_value_sp)

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

        val cFrom = Calendar.getInstance()
        val yearFrom = cFrom.get(Calendar.YEAR)
        val monthFrom = cFrom.get(Calendar.MONTH)
        val dayFrom = cFrom.get(Calendar.DAY_OF_MONTH)

        activity_hourly_production_data_date_value_tv.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                activity_hourly_production_data_date_value_tv.setText("" + year + "-${monthOfYear + 1}-" + dayOfMonth)
                viewModel.getHPD(this@HPDActivity, HPDSpNo,activity_hourly_production_data_date_value_tv.text.toString())
                setUpHourWises()
                subscribeToLiveDataHourWises()

            }, yearFrom, monthFrom, dayFrom)
            dpd.show()
        }

        activity_hourly_production_data_unit_name_value_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

                val map: HashMap<String, String> = AppConstants.HasHPDUnitNameList.get(position)
                HPDSpNo= map.get("unitNo")!!.toInt()
                Log.e("----------","-----------"+HPDSpNo)

                viewModel.getHPD(this@HPDActivity, HPDSpNo,activity_hourly_production_data_date_value_tv.text.toString())
                setUpHourWises()
                subscribeToLiveDataHourWises()

            }
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