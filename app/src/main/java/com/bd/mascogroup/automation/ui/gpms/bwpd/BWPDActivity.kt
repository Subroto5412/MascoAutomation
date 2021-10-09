package com.bd.mascogroup.automation.ui.gpms.bwpd

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
import com.bd.mascogroup.automation.databinding.ActivityBuyerWiseProductionDataBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.gpms.GPMSActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.LeaveApprovalActivity
import com.bd.mascogroup.automation.utils.AppConstants
import kotlinx.android.synthetic.main.activity_buyer_wise_production_data.*
import kotlinx.android.synthetic.main.activity_line_wise_production.*
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_header.layout_header_back_im
import java.util.*
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
    var buyerWiseSpNo:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityBuyerWiseProductionDataBinding = viewDataBinding
        viewModel.navigator = this
        mBWPDAdapter.setListener(this)
        activity_title_tv.setText("Buyer Wise Production \nData")

        activity_buyer_wise_production_data_date_value_tv.setText(viewModel.getCurrentDate())
        viewModel.getUnitName(this, activity_buyer_wise_production_data_unit_name_value_sp)

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

        activity_buyer_wise_production_data_date_value_tv.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                activity_buyer_wise_production_data_date_value_tv.setText("" + year + "-${monthOfYear + 1}-" + dayOfMonth)
                viewModel.getBWPD(this@BWPDActivity, buyerWiseSpNo,activity_buyer_wise_production_data_date_value_tv.text.toString())
                setUpStatus()
                subscribeToLiveDataBuyerWiseProductionStatus()
            }, yearFrom, monthFrom, dayFrom)
            dpd.show()
        }


        activity_buyer_wise_production_data_unit_name_value_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

                val map: HashMap<String, String> = AppConstants.HasBWPDUnitNameList.get(position)
                buyerWiseSpNo= map.get("unitNo")!!.toInt()
                Log.e("----------","-----------"+buyerWiseSpNo)

                viewModel.getBWPD(this@BWPDActivity, buyerWiseSpNo,activity_buyer_wise_production_data_date_value_tv.text.toString())
                setUpStatus()
                subscribeToLiveDataBuyerWiseProductionStatus()

            }
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