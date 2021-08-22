package com.bd.mascogroup.automation.ui.hr_info.leave_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityLeaveDetailsBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import javax.inject.Inject

class LeaveDetailsActivity : BaseActivity<ActivityLeaveDetailsBinding, LeaveDetailsViewModel>(), ILeaveDetailsNavigator {


    @Inject
    override lateinit var viewModel: LeaveDetailsViewModel

    private var mActivityLeaveDetailsBinding: ActivityLeaveDetailsBinding? = null

    @Inject
    lateinit var mLeaveDetailsViewModel: LeaveDetailsViewModel



    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_leave_details



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLeaveDetailsBinding = viewDataBinding
        viewModel.navigator = this

       /* layout_production_management_cl.setOnClickListener {
            val intent = ProductionManagementActivity.newIntent(this@HomeActivity)
            startActivity(intent)
        }*/
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LeaveDetailsActivity::class.java)
        }
    }
}