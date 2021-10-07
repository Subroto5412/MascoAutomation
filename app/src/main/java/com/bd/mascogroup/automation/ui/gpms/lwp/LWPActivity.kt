package com.bd.mascogroup.automation.ui.gpms.lwp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
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
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityLineWiseProductionBinding = viewDataBinding
        viewModel.navigator = this
        mLWPAdapter.setListener(this)
        viewModel.getLWP(this, 8,"2021-10-05")


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