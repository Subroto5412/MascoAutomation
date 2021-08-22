package com.bd.mascogroup.automation.ui.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.bd.mascogroup.automation.data.model.domainModel.ValidationModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.AppUtils

abstract class BaseDialog : DialogFragment() {

   // val currentDateTime = CommonUtils.getCurrentDataTime()
    var baseActivity: BaseActivity<*, *>? = null
        private set

    val isNetworkConnected: Boolean
        get() = baseActivity != null && baseActivity!!.isNetworkConnected


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            this.baseActivity = context
            context.onFragmentAttached()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // the content
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )

        // creating the fullscreen dialog
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        if (dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        dialog.setCanceledOnTouchOutside(false)
        this.isCancelable = false // Prevent Back button

    //    sendCustomEventToFirebase()

        return dialog
    }
 /*   fun sendCustomEventToFirebase() {
        val screenName =
                FirebaseAnalyticsExtension.FirebaseScreenMapping.fromString(this::class.java.simpleName)
        baseActivity?.let {
            screenName?.let { it }?.let { it1 ->
                it.firebaseAnalytics.sendScreenEvent(
                        it,
                        it1, arguments
                )
            }
        }
    }*/
    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun show(fragmentManager: FragmentManager, tag: String?) {
        val transaction = fragmentManager.beginTransaction()
        val prevFragment = fragmentManager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }

    fun enableBackButton() {
        this.isCancelable = true
    }

    fun dismissDialog(tag: String) {
        dismiss()
        baseActivity!!.onFragmentDetached(tag)
    }

    open fun onLogout() {

    }

    fun hideKeyboard() {
        if (baseActivity != null) {
            baseActivity!!.hideKeyboard()
        }
    }

    fun hideLoading() {
        if (baseActivity != null) {
            baseActivity!!.hideLoading()
        }
    }

    fun openActivityOnTokenExpire() {
        if (baseActivity != null) {
            baseActivity!!.openActivityOnTokenExpire()
        }
    }

    fun showLoading() {
        if (baseActivity != null) {
            baseActivity!!.showLoading()
        }
    }

//    fun showMessage(message: String) {
//        baseActivity!!.showMessage(message)
//    }
//
//    fun showMessage(message: String, messageType: AppUtils.MessageType) {
//        baseActivity!!.showMessage(message, messageType)
//    }

//    fun handleError(throwable: Throwable) {
//        baseActivity!!.handleError(throwable)
//    }


//    fun handleError(validationModel: ValidationModel) {
//        baseActivity!!.handleError(validationModel)
//    }
//
//    fun handleNoNetworkConnection(): Boolean {
//        return baseActivity!!.handleNoNetworkConnection()
//    }
//
//    fun handleAPIError(apiStatus: AppConstants.APIStatus) {
//        baseActivity!!.handleAPIError(apiStatus)
//    }
   /* fun setHighLuminance() {
        baseActivity?.setHighLuminance()
    }*/

   /* fun resetLuminance() {
        baseActivity?.resetLuminance()
    }*/
}