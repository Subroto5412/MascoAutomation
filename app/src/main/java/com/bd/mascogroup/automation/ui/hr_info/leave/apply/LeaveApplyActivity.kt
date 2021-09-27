package com.bd.mascogroup.automation.ui.hr_info.leave.apply

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
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityLeaveApplyBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivity
import com.bd.mascogroup.automation.ui.hr_info.leave.LeaveActivity
import com.bd.mascogroup.automation.utils.AppConstants
import kotlinx.android.synthetic.main.activity_leave_apply.*
import kotlinx.android.synthetic.main.activity_leave_details.*
import kotlinx.android.synthetic.main.activity_leave_details.leave_details_fyear_spinner
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_header.layout_header_back_im
import java.util.*
import javax.inject.Inject

class LeaveApplyActivity : BaseActivity<ActivityLeaveApplyBinding, LeaveApplyViewModel>(), ILeaveApplyNavigator {

    private var mActivityLeaveApplyBinding: ActivityLeaveApplyBinding? = null

    @Inject
    lateinit var mLeaveApplyViewModel: LeaveApplyViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_leave_apply

    override val viewModel: LeaveApplyViewModel
        get() {
            return mLeaveApplyViewModel
        }

    var leaveTypeNo:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLeaveApplyBinding = viewDataBinding
        viewModel.navigator = this

        activity_title_tv.setText("Leave Application Form")

        viewModel.dataSetting(this, activity_leave_apply_id_value, activity_leave_apply_name_value, activity_leave_apply_designation_value,activity_leave_apply_from_value,
            activity_leave_apply_to_value, activity_leave_apply_total_day_value, activity_leave_apply_pic_im)
        viewModel.getLeaveList(this, activity_leave_apply_apply_sp, activity_leave_apply_designation_value)

        layout_header_back_im.setOnClickListener {
            goToLeaveScreen()
        }
        layout_footer_home_im.setOnClickListener {
            val intent = HomeActivity.newIntent(this)
            startActivity(intent)
            finish()
        }
        activity_leave_apply_btn.setOnClickListener {
            if (leaveTypeNo==0){
                Toast.makeText(this, "Select Leave Type!", Toast.LENGTH_LONG).show()
            }else if (activity_leave_apply_reason_value.text.toString().equals("")){
                Toast.makeText(this, "Enter Reason Of Leave!", Toast.LENGTH_LONG).show()
            }else{
                viewModel.getLeaveApply(this@LeaveApplyActivity, leaveTypeNo,(activity_leave_apply_total_day_value.text.toString()).toInt(),
                        activity_leave_apply_from_value.text.toString(), activity_leave_apply_to_value.text.toString(), activity_leave_apply_reason_value.text.toString())
            }

        }

        val cFrom = Calendar.getInstance()
        val yearFrom = cFrom.get(Calendar.YEAR)
        val monthFrom = cFrom.get(Calendar.MONTH)
        val dayFrom = cFrom.get(Calendar.DAY_OF_MONTH)

        val cTo = Calendar.getInstance()
        val yearTo = cTo.get(Calendar.YEAR)
        val monthTo = cTo.get(Calendar.MONTH)
        val dayTo = cTo.get(Calendar.DAY_OF_MONTH)

        activity_leave_apply_from_value.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                activity_leave_apply_from_value.setText("" + year + "-${monthOfYear + 1}-" + dayOfMonth)
                viewModel.getLeaveDays(activity_leave_apply_from_value.text.toString(), activity_leave_apply_to_value.text.toString(),activity_leave_apply_total_day_value)
            }, yearFrom, monthFrom, dayFrom)
            dpd.show()
        }

        activity_leave_apply_to_value.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                activity_leave_apply_to_value.setText("" + year + "-${monthOfYear + 1}-" + dayOfMonth)
                viewModel.getLeaveDays(activity_leave_apply_from_value.text.toString(), activity_leave_apply_to_value.text.toString(),activity_leave_apply_total_day_value)
            }, yearTo, monthTo, dayTo)
            dpd.show()
        }

        activity_leave_apply_apply_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

                val map: HashMap<String, String> = AppConstants.HasLeaveList.get(position)
                activity_leave_apply_rest_value.setText(map.get("avail")!!)
                leaveTypeNo = map.get("leaveTypeNo")!!.toInt()
            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LeaveApplyActivity::class.java)
        }
    }

    override fun goToLeaveScreen(){
        val intent = LeaveActivity.newIntent(this)
        startActivity(intent)
        finish()
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goToLeaveScreen()
        }
        return super.onKeyDown(keyCode, event)
    }
}