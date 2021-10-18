package com.bd.mascogroup.automation.ui.atm.asset

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.databinding.DialogAssetScannerBinding
import com.bd.mascogroup.automation.ui.atm.ATMActivity
import com.bd.mascogroup.automation.ui.base.BaseActivity
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.dialog_asset_scanner.*
import javax.inject.Inject

class AssetActivity : BaseActivity<DialogAssetScannerBinding, AssetViewModel>(), IAssetNavigator {

    private var mDialogAssetScannerBinding: DialogAssetScannerBinding? = null

    @Inject
    lateinit var mAssetViewModel: AssetViewModel

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int

        get() = R.layout.dialog_asset_scanner

    override val viewModel: AssetViewModel
        get() {
            return mAssetViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDialogAssetScannerBinding = viewDataBinding
        viewModel.navigator = this

        val intentIntegrator = IntentIntegrator(this)
        intentIntegrator.setBeepEnabled(false)
        intentIntegrator.setCameraId(0)
        intentIntegrator.setPrompt("SCAN")
        intentIntegrator.setBarcodeImageEnabled(false)
        intentIntegrator.initiateScan()

        scan_another_qr_btn.setOnClickListener {
            finish()
            val intent = AssetActivity.newIntent(this)
            startActivity(intent)
          }

        scan_popup_cancel_btn.setOnClickListener {
            finish()
            val intent = ATMActivity.newIntent(this)
            startActivity(intent)
         }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AssetActivity::class.java)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            val intent = ATMActivity.newIntent(this)
            startActivity(intent)
            finish()

        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.getProductScanner(
                    this,
                    result.contents,
                    aad_asset_no_value_tv,
                    aad_asset_name_value_tv,
                    aad_unit_value_tv,
                    aad_purchase_date_value_tv,
                    aad_purchase_value_value_tv,
                    aad_asset_entry_date_value_tv
                )
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}