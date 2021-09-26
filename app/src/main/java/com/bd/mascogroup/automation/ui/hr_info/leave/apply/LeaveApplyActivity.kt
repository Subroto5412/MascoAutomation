package com.bd.mascogroup.automation.ui.hr_info.leave.apply

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
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityLeaveApplyBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivity
import com.bd.mascogroup.automation.utils.AppConstants
import kotlinx.android.synthetic.main.activity_leave_apply.*
import kotlinx.android.synthetic.main.activity_leave_details.*
import kotlinx.android.synthetic.main.activity_leave_details.leave_details_fyear_spinner
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import java.util.ArrayList
import java.util.HashMap
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

    private var leaveListSpId = ArrayList<String>()
    private var leaveListSpName = ArrayList<String>()
    private var leaveListSpAvail = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLeaveApplyBinding = viewDataBinding
        viewModel.navigator = this

        viewModel.dataSetting(activity_leave_apply_id_value, activity_leave_apply_name_value, activity_leave_apply_designation_value)
        viewModel.getLeaveList(this, activity_leave_apply_apply_sp, activity_leave_apply_designation_value)

        layout_header_back_im.setOnClickListener {
            val intent = HRInfoActivity.newIntent(this)
            startActivity(intent)
            finish()
        }
        layout_footer_home_im.setOnClickListener {
            val intent = HomeActivity.newIntent(this)
            startActivity(intent)
            finish()
        }

        leaveListSpId.clear()
        leaveListSpName.clear()
        leaveListSpAvail.clear()
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
            }
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LeaveApplyActivity::class.java)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            val intent = HRInfoActivity.newIntent(this)
            startActivity(intent)
            finish()

        }
        return super.onKeyDown(keyCode, event)
    }
}