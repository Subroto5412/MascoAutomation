package com.bd.mascogroup.automation.ui.hr_info.income_tax

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.model.domainModel.IncomeTaxDeductionCardData
import com.bd.mascogroup.automation.databinding.ActivityIcomeTaxDeductionBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivity
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import javax.inject.Inject

class IncomeTaxDeductionActivity : BaseActivity<ActivityIcomeTaxDeductionBinding, IncomeTaxDeductionViewModel>(), IIncomeTaxDeductionNavigator, IncomeTaxDeductionAdapter.IncomeTaxDeductionAdapterListener {



    lateinit var mActivityIcomeTaxDeductionBinding: ActivityIcomeTaxDeductionBinding

    @Inject
    lateinit var mIncomeTaxDeductionViewModel: IncomeTaxDeductionViewModel

    @Inject
    lateinit var mIncomeTaxDeductionAdapter: IncomeTaxDeductionAdapter


    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_icome_tax_deduction

    override val viewModel: IncomeTaxDeductionViewModel
        get() {
            return mIncomeTaxDeductionViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityIcomeTaxDeductionBinding = viewDataBinding
        viewModel.navigator = this

        mIncomeTaxDeductionAdapter.setListener(this)
        activity_title_tv.setText("Income Tax Deduction \nHistory")

        viewModel.IncomeTaxDeduction(this)
        setUpIcomeTaxDeduction()
        subscribeToLiveDataIncomeTaxDeduction()

        layout_header_back_im.setOnClickListener {
            val intent = HRInfoActivity.newIntent(this@IncomeTaxDeductionActivity)
            startActivity(intent)
        }

        layout_footer_home_im.setOnClickListener {
            val intent = HomeActivity.newIntent(this)
            startActivity(intent)
            finish()
        }

    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, IncomeTaxDeductionActivity::class.java)
        }
    }

    fun setUpIcomeTaxDeduction() {
        mActivityIcomeTaxDeductionBinding.activityIncomeTaxDeductionListRv.itemAnimator = DefaultItemAnimator()
        mActivityIcomeTaxDeductionBinding.activityIncomeTaxDeductionListRv.adapter = mIncomeTaxDeductionAdapter
    }

    fun updateIncomeTaxDeductionList(incomeTaxDeductionCardData: List<IncomeTaxDeductionCardData>?) {
        mIncomeTaxDeductionAdapter.clearItems()
        if (!incomeTaxDeductionCardData.isNullOrEmpty()) {
            mIncomeTaxDeductionAdapter.addItem(incomeTaxDeductionCardData)
        }
    }

    fun subscribeToLiveDataIncomeTaxDeduction() {
        mIncomeTaxDeductionViewModel.getincomeTaxDeductionLiveData().observe(this, Observer { t ->
            mIncomeTaxDeductionViewModel.addIncomeTaxDeductionItemToList(t)
            updateIncomeTaxDeductionList(t)
        })
    }

}