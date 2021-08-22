package com.bd.mascogroup.automation.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityHomeBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivity
import com.bd.mascogroup.automation.ui.production_management.ProductionManagementActivity
import com.bd.mascogroup.automation.ui.signup.SignupActivity
import kotlinx.android.synthetic.main.activity_hr_info.*
import kotlinx.android.synthetic.main.layout_home_body.*
import kotlinx.android.synthetic.main.layout_home_body.layout_human_resource_cl
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), IHomeNavigator {


    @Inject
    override lateinit var viewModel: HomeViewModel

    private var mActivityHomeBinding: ActivityHomeBinding? = null

    @Inject
    lateinit var mHomeViewModel: HomeViewModel



    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_home



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityHomeBinding = viewDataBinding
        viewModel.navigator = this

        layout_production_management_cl.setOnClickListener {
            val intent = ProductionManagementActivity.newIntent(this@HomeActivity)
            startActivity(intent)
        }

        layout_human_resource_cl.setOnClickListener {
            val intent = HRInfoActivity.newIntent(this@HomeActivity)
            startActivity(intent)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}