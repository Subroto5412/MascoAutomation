package com.bd.mascogroup.automation.ui.production_management

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityProductionManagementBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
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
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ProductionManagementActivity::class.java)
        }
    }
}