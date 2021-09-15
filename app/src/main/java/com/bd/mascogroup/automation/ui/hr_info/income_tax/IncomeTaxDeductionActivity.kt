package com.bd.mascogroup.automation.ui.hr_info.income_tax

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
import com.bd.mascogroup.automation.data.model.domainModel.IncomeTaxDeductionCardData
import com.bd.mascogroup.automation.databinding.ActivityIcomeTaxDeductionBinding
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import com.bd.mascogroup.automation.ui.hr_info.HRInfoActivity
import com.bd.mascogroup.automation.utils.AppConstants
import kotlinx.android.synthetic.main.activity_icome_tax_deduction.*
import kotlinx.android.synthetic.main.activity_leave_details.*
import kotlinx.android.synthetic.main.activity_leave_details.leave_details_fyear_spinner
import kotlinx.android.synthetic.main.layout_common_header.*
import kotlinx.android.synthetic.main.layout_footer.*
import java.util.HashMap
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

    var taxYearNo:Int = 0
    var yearName:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityIcomeTaxDeductionBinding = viewDataBinding
        viewModel.navigator = this

        mIncomeTaxDeductionAdapter.setListener(this)
        activity_title_tv.setText("Income Tax Deduction \nHistory")

        viewModel.getFinancialYear(this, income_tax_year_spinner)
        layout_header_back_im.setOnClickListener {
            val intent = HRInfoActivity.newIntent(this@IncomeTaxDeductionActivity)
            startActivity(intent)
            finish()
        }

        layout_footer_home_im.setOnClickListener {
            val intent = HomeActivity.newIntent(this)
            startActivity(intent)
            finish()
        }

        income_tax_year_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

                val map: HashMap<String, String> = AppConstants.HasYearTaxList.get(position)
                taxYearNo = map.get("taxYearNo")!!.toInt()
                yearName = map.get("yearName")!!

                viewModel.IncomeTaxDeduction(this@IncomeTaxDeductionActivity, taxYearNo,activity_income_tax_total_tv,activity_income_tax_total_value_tv)
                setUpIcomeTaxDeduction()
                subscribeToLiveDataIncomeTaxDeduction()
            }
        }

    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, IncomeTaxDeductionActivity::class.java)
        }
    }

    override fun openHRScreen(){
        val intent = HRInfoActivity.newIntent(this@IncomeTaxDeductionActivity)
        startActivity(intent)
        finish()
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val intent = HRInfoActivity.newIntent(this)
            startActivity(intent)
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }
}