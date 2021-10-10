package com.bd.mascogroup.automation.ui.production_management

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityProductionManagementBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.gpms.GPMSActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info.attendance.AttendanceActivity
import com.bd.mascogroup.automation.ui.hr_info.leave.LeaveActivity
import com.bd.mascogroup.automation.ui.hr_info.tax.TaxActivity
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.android.synthetic.main.layout_production_management.*
import javax.inject.Inject

class ProductionManagementActivity : BaseActivity<ActivityProductionManagementBinding, ProductionManagementViewModel>(), IProductionManagementNavigator {


    @Inject
    override lateinit var viewModel: ProductionManagementViewModel

    private var mActivityProductionManagementBinding: ActivityProductionManagementBinding? = null

    @Inject
    lateinit var mProductionManagementViewModel: ProductionManagementViewModel



    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_production_management



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityProductionManagementBinding = viewDataBinding
        viewModel.navigator = this
        viewModel.getSearchName(this, layout_header_search_actv)
        layout_production_management_cl.setOnClickListener {

            val intent = GPMSActivity.newIntent(this)
            startActivity(intent)
            finish()
        }

        layout_header_back_im.setOnClickListener {
            val intent = HomeActivity.newIntent(this)
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
            return Intent(context, ProductionManagementActivity::class.java)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            val intent = HomeActivity.newIntent(this)
            startActivity(intent)
            finish()

        }
        return super.onKeyDown(keyCode, event)
    }

    override fun openAttendanceActivity(){
        val intent = AttendanceActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun openLeaveActivity(){
        val intent = LeaveActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun openTaxActivity(){
        val intent = TaxActivity.newIntent(this)
        startActivity(intent)
        finish()
    }
}