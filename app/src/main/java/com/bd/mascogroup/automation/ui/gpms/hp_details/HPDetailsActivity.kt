package com.bd.mascogroup.automation.ui.gpms.hp_details

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
import com.bd.mascogroup.automation.data.model.domainModel.HourlyProductionDetailsCardData
import com.bd.mascogroup.automation.databinding.ActivityHourlyProductionDetailsBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.gpms.GPMSActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.utils.AppConstants
import kotlinx.android.synthetic.main.activity_buyer_wise_production_data.*
import kotlinx.android.synthetic.main.activity_hourly_production_details.*
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import java.util.*
import javax.inject.Inject

class HPDetailsActivity : BaseActivity<ActivityHourlyProductionDetailsBinding, HPDetailsViewModel>(), IHPDetailsNavigator, HPDetailsAdapter.HPDetailsAdapterListener {

    private lateinit var mActivityHourlyProductionDetailsBinding: ActivityHourlyProductionDetailsBinding

    @Inject
    lateinit var mHPDetailsAdapter: HPDetailsAdapter

    @Inject
    lateinit var mHPDetailsViewModel: HPDetailsViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_hourly_production_details

    override val viewModel: HPDetailsViewModel
        get() {
            return mHPDetailsViewModel
        }
    var hourlyProductionDetailsSpNo:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityHourlyProductionDetailsBinding = viewDataBinding
        viewModel.navigator = this
        mHPDetailsAdapter.setListener(this)
        activity_title_tv.setText("Hourly Production \nDetails")

        activity_hourly_production_details_date_value_tv.setText(viewModel.getCurrentDate())
        viewModel.getUnitName(this, activity_hourly_production_details_unit_name_value_sp)

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

        activity_hourly_production_details_date_value_tv.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                activity_hourly_production_details_date_value_tv.setText("" + year + "-${monthOfYear + 1}-" + dayOfMonth)

                viewModel.getHPDetails(this@HPDetailsActivity, hourlyProductionDetailsSpNo,activity_hourly_production_details_date_value_tv.text.toString(),
                        activity_hourly_production_details_sew_output_value_tv, activity_hourly_production_details_line_input_value_tv_tv, activity_hourly_production_details_cutting_value_tv,
                        activity_hourly_production_details_carton_value_tv,activity_hourly_production_details_total_ploy_value_tv, activity_hourly_production_details_folding_value_tv,
                        activity_hourly_production_details_total_iron_value_tv)
                setUpHourlyProductionDetails()
                subscribeToLiveDataHourlyProductionDetails()
            }, yearFrom, monthFrom, dayFrom)
            dpd.show()
        }


        activity_hourly_production_details_unit_name_value_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

                val map: HashMap<String, String> = AppConstants.HasHPDetailsUnitNameList.get(position)
                hourlyProductionDetailsSpNo= map.get("unitNo")!!.toInt()

                viewModel.getHPDetails(this@HPDetailsActivity, hourlyProductionDetailsSpNo,activity_hourly_production_details_date_value_tv.text.toString(),
                        activity_hourly_production_details_sew_output_value_tv, activity_hourly_production_details_line_input_value_tv_tv, activity_hourly_production_details_cutting_value_tv,
                        activity_hourly_production_details_carton_value_tv,activity_hourly_production_details_total_ploy_value_tv, activity_hourly_production_details_folding_value_tv,
                        activity_hourly_production_details_total_iron_value_tv)
                setUpHourlyProductionDetails()
                subscribeToLiveDataHourlyProductionDetails()

            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HPDetailsActivity::class.java)
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


    fun setUpHourlyProductionDetails() {
        mActivityHourlyProductionDetailsBinding.hourlyProductionDetailsListParentRv.itemAnimator = DefaultItemAnimator()
        mActivityHourlyProductionDetailsBinding.hourlyProductionDetailsListParentRv.adapter = mHPDetailsAdapter
    }

    fun updatHourlyProductionDetailsList(hourlyProductionDetailsCardData: List<HourlyProductionDetailsCardData>?) {
        mHPDetailsAdapter.clearItems()
        if (!hourlyProductionDetailsCardData.isNullOrEmpty()) {
            mHPDetailsAdapter.addItem(hourlyProductionDetailsCardData)
        }
    }

   fun subscribeToLiveDataHourlyProductionDetails() {
        mHPDetailsViewModel.getHPDetailLiveData().observe(this, Observer { t ->
            mHPDetailsViewModel.addHPDetailItemToList(t)
            updatHourlyProductionDetailsList(t)
        })
    }
}