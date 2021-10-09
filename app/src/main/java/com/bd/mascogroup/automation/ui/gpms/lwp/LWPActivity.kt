package com.bd.mascogroup.automation.ui.gpms.lwp

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
import com.bd.mascogroup.automation.data.model.domainModel.HourWiseCardData
import com.bd.mascogroup.automation.data.model.domainModel.LineWiseCardData
import com.bd.mascogroup.automation.databinding.ActivityLineWiseProductionBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.gpms.GPMSActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info_system.leave_approval.LeaveApprovalActivity
import com.bd.mascogroup.automation.utils.AppConstants
import kotlinx.android.synthetic.main.activity_leave_apply.*
import kotlinx.android.synthetic.main.activity_leave_details.*
import kotlinx.android.synthetic.main.activity_leave_details.leave_details_fyear_spinner
import kotlinx.android.synthetic.main.activity_line_wise_production.*
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_header.layout_header_back_im
import java.util.*
import javax.inject.Inject


class LWPActivity : BaseActivity<ActivityLineWiseProductionBinding, LWPViewModel>(), ILWPNavigator, LWPAdapter.LWPAdapterListener {

    private lateinit var mActivityLineWiseProductionBinding: ActivityLineWiseProductionBinding

    @Inject
    lateinit var mLWPViewModel: LWPViewModel

    @Inject
    lateinit var mLWPAdapter: LWPAdapter

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_line_wise_production

    override val viewModel: LWPViewModel
        get() {
            return mLWPViewModel
        }

    var lineWiseSpNo:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLineWiseProductionBinding = viewDataBinding
        viewModel.navigator = this
        mLWPAdapter.setListener(this)
        activity_title_tv.setText("Line Wise Production")

        activity_line_wise_production_date_value_tv.setText(viewModel.getCurrentDate())
        viewModel.getUnitName(this, activity_line_wise_production_unit_name_value_sp)

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

        activity_line_wise_production_date_value_tv.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                activity_line_wise_production_date_value_tv.setText("" + year + "-${monthOfYear + 1}-" + dayOfMonth)

                viewModel.getLWP(this@LWPActivity, lineWiseSpNo,activity_line_wise_production_date_value_tv.text.toString(),activity_line_wise_output_value_tv)
                setUpLineWises()
                subscribeToLiveDataLineWises()
            }, yearFrom, monthFrom, dayFrom)
            dpd.show()
        }

        activity_line_wise_production_unit_name_value_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

                val map: HashMap<String, String> = AppConstants.HasLWPUnitNameList.get(position)
                lineWiseSpNo= map.get("unitNo")!!.toInt()

                viewModel.getLWP(this@LWPActivity, lineWiseSpNo,activity_line_wise_production_date_value_tv.text.toString(),activity_line_wise_output_value_tv)
                setUpLineWises()
                subscribeToLiveDataLineWises()
            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LWPActivity::class.java)
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

    fun setUpLineWises() {
        mActivityLineWiseProductionBinding.activityLineWiseProductionListRv.itemAnimator = DefaultItemAnimator()
        mActivityLineWiseProductionBinding.activityLineWiseProductionListRv.adapter = mLWPAdapter
    }

    fun updatLineWisesList(lineWiseCardData: List<LineWiseCardData>?) {
        mLWPAdapter.clearItems()
        if (!lineWiseCardData.isNullOrEmpty()) {
            mLWPAdapter.addItem(lineWiseCardData)
        }
    }

    fun subscribeToLiveDataLineWises() {
        mLWPViewModel.getLWPLiveData().observe(this, Observer { t ->
            mLWPViewModel.addLWPItemToList(t)
            updatLineWisesList(t)
        })
    }

}