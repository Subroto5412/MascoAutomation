package com.bd.mascogroup.automation.ui.atm.asset

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.IDataManager
import com.bd.mascogroup.automation.data.remote.ApiServiceCalling
import com.bd.mascogroup.automation.data.remote.domainModel.AssetDataDetailsRequest
import com.bd.mascogroup.automation.ui.base.BaseViewModel
import com.bd.mascogroup.automation.utils.UtilMethods
import com.bd.mascogroup.automation.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AssetViewModel @Inject constructor(
        dataManager: IDataManager,
        ISchedulerProvider: ISchedulerProvider
): BaseViewModel<IAssetNavigator>(dataManager, ISchedulerProvider) {


        fun getProductScanner(
                context: Context,
                productScannerItem: String,
                asset_no_value_tv: TextView, asset_name_value_tv: TextView, unit_value_tv: TextView, purchase_date_value_tv: TextView, purchase_value_value_tv: TextView, asset_entry_date_value_tv:TextView
        ) {
                if (UtilMethods.isConnectedToInternet(context)) {
                        UtilMethods.showLoading(context)
                        val observable = ApiServiceCalling.generalMisApiCall().getAssetDetails(
                                AssetDataDetailsRequest(productScannerItem)
                        )

                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ scannerResponse ->

                                        if (scannerResponse.assetDataDetails == null) {
                                                Toast.makeText(context, "Product Not Found!", Toast.LENGTH_LONG).show()
                                        } else {
                                                asset_no_value_tv.setText(scannerResponse.assetDataDetails.assetNo)
                                                asset_name_value_tv.setText(scannerResponse.assetDataDetails.assetName)
                                                unit_value_tv.setText(scannerResponse.assetDataDetails.unitName)
                                                purchase_date_value_tv.setText(scannerResponse.assetDataDetails.purchaseDate)
                                                purchase_value_value_tv.setText(scannerResponse.assetDataDetails.purchaseValue)
                                                asset_entry_date_value_tv.setText(scannerResponse.assetDataDetails.assetEntryDate)
                                        }

                                        UtilMethods.hideLoading()
                                }, { error ->
                                        UtilMethods.hideLoading()
                                        // UtilMethods.showLongToast(context, error.message.toString())
                                }
                                )
                } else {
                        UtilMethods.showLongToast(context, "No Internet Connection!")
                }
        }
}