package com.bd.mascogroup.automation.ui.atm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.ActivityAtmBinding
import com.bd.mascogroup.automation.ui.atm.asset.AssetActivity
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.bd.mascogroup.automation.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_atm.*
import kotlinx.android.synthetic.main.layout_footer.*
import kotlinx.android.synthetic.main.layout_header.*
import javax.inject.Inject

class ATMActivity : BaseActivity<ActivityAtmBinding, ATMViewModel>(), IATMNavigator {

    private var mActivityAtmBinding: ActivityAtmBinding? = null

    @Inject
    lateinit var mATMViewModel: ATMViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.activity_atm

    override val viewModel: ATMViewModel
        get() {
            return mATMViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityAtmBinding = viewDataBinding
        viewModel.navigator = this

        activity_atm_asset_basic_data_cl.setOnClickListener {
            val intent = AssetActivity.newIntent(this)
            startActivity(intent)
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
            return Intent(context, ATMActivity::class.java)
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
}