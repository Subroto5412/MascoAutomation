package com.bd.mascogroup.automation.ui.base

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.gson.JsonParser
import com.bd.mascogroup.automation.R
import com.bd.mascogroup.automation.data.model.domainModel.ValidationModel
import com.bd.mascogroup.automation.utils.AppConstants
import com.bd.mascogroup.automation.utils.AppUtils
import com.bd.mascogroup.automation.utils.NetworkUtils
import com.bd.mascogroup.automation.utils.PermissionUtils
import com.bd.mascogroup.automation.utils.extension.ContextExtension.showLoadingDialog
import dagger.android.AndroidInjection
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.security.cert.CertificateException
import java.util.*
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.inject.Singleton
import javax.net.ssl.SSLHandshakeException



abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(),
        BaseFragment.Callback {

//    val currentDateTime = CommonUtils.getCurrentDataTime()
    private var mProgressDialog: ProgressBar? = null
    lateinit var viewDataBinding: T
        private set
    private lateinit var mViewModel: V

    //@Singleton
  //  lateinit var mDialogView: DialogView

    abstract val bindingVariable: Int

    @get:LayoutRes
    abstract val layoutId: Int
    abstract val viewModel: V

  //  private lateinit var mFusedLocationClient: FusedLocationProviderClient
   // private var locationRequest: LocationRequest? = null
   // private var locationCallback: LocationCallback? = null
  //  private var wayLatitude = AppConstants.Number.ZERO.value.toDouble()
 //   private var wayLongitude = AppConstants.Number.ZERO.value.toDouble()
    private var isGPS = false
    var mLocationPermissionGranted = false

//    lateinit var nifty: INiftyCloud
//
//    @Inject
//    lateinit var mGPSUtils: GPSUtils


//    @Singleton
//    val firebaseAnalytics: FirebaseAnalytics
//        get() = FirebaseAnalytics.getInstance(applicationContext)

    val isNetworkConnected: Boolean
        get() = NetworkUtils.isNetworkConnected(applicationContext)

    // Here 1 represent max location result to returned, by documents it recommended 1 to 5
    // Here 1 represent max location result to returned, by documents it recommended 1 to 5
    //e.printStackTrace();
   /* val addressFromLatLong: android.location.Address?
        get() {
            if (PermissionUtils.hasPermissions(this, *LOCATION_PERMISSION_LIST)) {
                val str = mViewModel.dataManager.currentUserLatLong
                if (str != null && str.size > 0) {
                    val longitude = java.lang.Double.parseDouble(str[1])
                    val latitude = java.lang.Double.parseDouble(str[0])

                    try {
                        val geocoder: Geocoder
                        var addresses: List<Address>
                        geocoder = Geocoder(this, Locale.getDefault())
                        try {
                            addresses = geocoder.getFromLocation(latitude, longitude, 1)
                        } catch (e: Exception) {
                            addresses = geocoder.getFromLocation(latitude, longitude, 1)
                        }

                        if (addresses.isNotEmpty()) {
                            return addresses[0]
                        }

                    } catch (e: Exception) {
                    }

                }
            }
            return null
        }*/

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
      //  mDialogView = DialogView.newInstance.getInstance(this)
   //     nifty = NiftyCloud()
       // nifty.initialize(applicationContext)
        performDataBinding()

     //   sendCustomEventToFirebase()
    }

   /* fun sendCustomEventToFirebase() {
        val screenName =
                FirebaseAnalyticsExtension.FirebaseScreenMapping.fromString(this::class.java.simpleName)
        screenName?.let { firebaseAnalytics.sendScreenEvent(this, it, intent.extras) }
    }*/

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun hideLoading() {
        //mProgressDialog!!.cancel()
        mProgressDialog?.visibility = View.INVISIBLE

    }

    fun openActivityOnTokenExpire() {
        //startActivity(LoginActivity.newIntent(this))
        //finish()
    }

    fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    fun showLoading() {
        hideLoading()
        mProgressDialog = this.showLoadingDialog()
        mProgressDialog?.visibility = View.VISIBLE
    }

    private fun performDataBinding() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar()?.hide(); //hide the title bar
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.mViewModel = viewModel //if (mViewModel == null) viewModel else mViewModel
        viewDataBinding.setVariable(bindingVariable, mViewModel)
        viewDataBinding.executePendingBindings()
    }

   /* private fun defaultErrorHandler(throwable: Throwable) {
        val status: String
        val message: String
        try {
            val error = (throwable as ANError).errorBody
            if (!error.isBlank()) {
                try {
                    val json = JSONTokener(error).nextValue()
                    if (json is JSONObject) {
                        val o = JsonParser.parseString(error).asJsonObject
                        if (o != null) {
                            status =
                                    if (o.get("status") == null) EMPTY_STRING else o.get("status").asString
                            message =
                                    if (o.get("message") == null) EMPTY_STRING else o.get("message").asString

                            when (status) {
                                "999" -> {

                                }
                                else -> {
                                    mViewModel.forceLogout()
                                }
                            }

                        }
                    } else if (json is JSONArray) {
                        //
                    }//you have an object
                } catch (ex: Exception) {
                    //
                }

            } else {
                //Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                if (throwable.message.isNullOrEmpty()) {
                    //showMessage("Something went wrong, please try again.")
                } else {
                    showMessage(throwable.message.toString())
                }
            }
        } catch (ex: Exception) {
            //showMessage("Something went wrong, please try again.")
        }

    }*/

    /*fun handleError(throwable: Throwable) {
        try {
            if (throwable is TimeoutException || throwable.cause is TimeoutException) {
                showMessage(getString(R.string.apiTimeoutMessage))
            } else if (throwable is SSLHandshakeException || throwable.cause is SSLHandshakeException || throwable is CertificateException || throwable.cause is CertificateException) {
                mDialogView.setMessage(getString(R.string.sslCertificateExpireMessage))
                mDialogView.show(this.supportFragmentManager) { handleSSLHandshakeException() }
            } else {
                defaultErrorHandler(throwable)
            }
        } catch (ex: Exception) {
            //showMessage("Something went wrong, please try again.")
        }
    }*/

/*    open fun handleSSLHandshakeException() {
        viewModel.dataManager.setUserAsLoggedOut()
    }*/

//    fun handleError(validationModel: ValidationModel) {
//        handleNoNetworkConnection()
//        val control = validationModel.control as EditText?
//        if (control != null) {
//            control.requestFocus()
//            control.error = validationModel.value
//            //Toast.makeText(this, validationModel.getValue(), Toast.LENGTH_SHORT).show();
//            showMessage(validationModel.value)
//        }
//    }

//    fun handleAPIError(apiStatus: AppConstants.APIStatus) {
//        mDialogView.setMessageType(AppUtils.MessageType.API_STATUS, apiStatus)
//        mDialogView.show(supportFragmentManager)
//    }
//
//    fun showMessage(message: String) {
//        mDialogView.setMessage(message)
//        mDialogView.show(supportFragmentManager)
//    }
//
//    fun showMessage(message: String, messageType: AppUtils.MessageType) {
//        mDialogView.setMessage(message, messageType)
//        mDialogView.show(supportFragmentManager)
//    }
//
//    open fun handleNoNetworkConnection(): Boolean {
//        if (!isNetworkConnected) {
//            mDialogView.setMessageType(AppUtils.MessageType.INTERNET_NOT_FOUND)
//            mDialogView.show(supportFragmentManager)
//        }
//        return true
//    }

   /* fun setHighLuminance() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        val params = window.attributes
        viewModel.dataManager.putFloat(
                PreferencesHelper.SharedKeys.PREF_KEY_CURRENT_DEVICE_LUMINOUS,
                params.screenBrightness
        )
        params.screenBrightness = AppConstants.Number.ONE.value.toFloat()
        window.attributes = params
    }*/

   /* fun resetLuminance() {
        if (viewModel.dataManager.contains(PreferencesHelper.SharedKeys.PREF_KEY_CURRENT_DEVICE_LUMINOUS)) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            val params = window.attributes

            params.screenBrightness =
                    viewModel.dataManager.getFloat(PreferencesHelper.SharedKeys.PREF_KEY_CURRENT_DEVICE_LUMINOUS)
            window.attributes = params
        }
    }*/

    /*fun getDeviceLocation() {
        mLocationPermissionGranted = viewModel.dataManager.getBoolean(
                PreferencesHelper.SharedKeys.PREF_KEY_LOCATION_PERMISSION_GRANTED,
                false
        )
        if (mLocationPermissionGranted) {
            mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                val location: Location? = task.result
                if (location == null) {
                    mFusedLocationClient.requestLocationUpdates(
                            locationRequest,
                            locationCallback,
                            null
                    )
                } else {
                    wayLatitude = location.latitude
                    wayLongitude = location.longitude
                    viewModel.dataManager.currentUserLatLong =
                            arrayOf(wayLatitude.toString(), wayLongitude.toString())
                }
            }
        }
    }*/

    /*fun gpsSetup() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.create()
        locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest?.interval =
                (AppConstants.Number.TEN.value * AppConstants.Number.THOUSAND.value).toLong()// 10 seconds
        locationRequest?.fastestInterval =
                (AppConstants.Number.FIVE.value * AppConstants.Number.THOUSAND.value).toLong() // 5 seconds


        mGPSUtils.turnGPSOn(object : GPSUtils.onGpsListener {
            override fun gpsStatus(isGPSEnable: Boolean) { // turn on GPS
                isGPS = isGPSEnable
            }
        })

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult): Unit {
                for (location: Location? in locationResult.locations) {
                    if (location != null) {
                        wayLatitude = location.latitude
                        wayLongitude = location.longitude
                        viewModel.dataManager.currentUserLatLong =
                                arrayOf(wayLatitude.toString(), wayLongitude.toString())

                        mFusedLocationClient.removeLocationUpdates(locationCallback)
                    }
                }
            }
        }
    }*/

    /*fun allPermissionAsk() {
        if (!PermissionUtils.hasPermissions(this, *AppPermission.ALL_PERMISSION_LIST)) {
            ActivityCompat.requestPermissions(
                    this,
                    AppPermission.ALL_PERMISSION_LIST,
                    AppPermission.ALL_PERMISSION_LIST_CODE
            )
        }
        if (!PermissionUtils.hasPermissions(this, *LOCATION_PERMISSION_LIST)) {
            requestPermissionsSafely(
                    LOCATION_PERMISSION_LIST,
                    AppPermission.MY_PHONE_STATE_PERMISSION_CODE
            )
        }
    }
*/
   /* override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if ((requestCode == AppPermission.MY_LOCATION_PERMISSION_CODE || requestCode == AppPermission.ALL_PERMISSION_LIST_CODE) && grantResults.isNotEmpty()) {
            if (grantResults[AppConstants.Number.ZERO.value] == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true
                viewModel.dataManager.putBoolean(
                        PreferencesHelper.SharedKeys.PREF_KEY_LOCATION_PERMISSION_GRANTED,
                        mLocationPermissionGranted
                )
                getDeviceLocation()
            }
        }
    }*/
}