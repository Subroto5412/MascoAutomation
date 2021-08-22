package com.bd.mascogroup.automation.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.bd.mascogroup.automation.data.model.domainModel.ValidationModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.AppUtils
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {
   // val currentDateTime = CommonUtils.getCurrentDataTime()
    var baseActivity: BaseActivity<*, *>? = null
        private set
    private var mRootView: View? = null
    var viewDataBinding: T? = null
        private set
    private var mViewModel: V? = null
    abstract val bindingVariable: Int
    abstract val TAG: String

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModel: V

    val isNetworkConnected: Boolean
        get() {
            baseActivity?.let {
                return it.isNetworkConnected
            }
            return false
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            this.baseActivity = context
            context.onFragmentAttached()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        mViewModel = viewModel
        setHasOptionsMenu(false)
    }



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = viewDataBinding!!.root
        return mRootView
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.setVariable(bindingVariable, mViewModel)
        viewDataBinding?.executePendingBindings()
    }

    fun hideKeyboard() {
        if (baseActivity != null) {
            baseActivity?.hideKeyboard()
        }
    }

    open fun onLogout() {

    }

    fun openActivityOnTokenExpire() {
        if (baseActivity != null) {
            baseActivity?.openActivityOnTokenExpire()
        }
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }


//    fun handleError(throwable: Throwable) {
//        baseActivity?.handleError(throwable)
//    }


//    fun handleError(validationModel: ValidationModel) {
//        baseActivity?.handleError(validationModel)
//    }
//
//    fun handleNoNetworkConnection(): Boolean {
//        baseActivity?.let { return it.handleNoNetworkConnection() }
//        return false
//    }
//
//    fun handleAPIError(apiStatus: AppConstants.APIStatus) {
//        baseActivity?.handleAPIError(apiStatus)
//    }
//
//    fun showMessage(message: String, messageType: AppUtils.MessageType) {
//        baseActivity?.showMessage(message, messageType)
//    }
//
//    fun showMessage(message: String) {
//        baseActivity?.showMessage(message)
//    }

    fun showLoading() {
        baseActivity?.showLoading()
    }

    fun hideLoading() {
        baseActivity?.hideLoading()
    }

   /* fun setHighLuminance() {
        baseActivity?.setHighLuminance()
    }*/

    /*fun resetLuminance() {
        baseActivity?.resetLuminance()
    }*/

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }
}