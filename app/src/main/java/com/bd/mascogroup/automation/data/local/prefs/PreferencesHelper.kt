package com.bd.mascogroup.automation.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import com.bd.mascogroup.automation.di.PreferenceInfo
import javax.inject.Inject

class PreferencesHelper @Inject constructor(context: Context, @PreferenceInfo prefInfo: String):IPreferencesHelper{

    private val mPrefs: SharedPreferences =
            context.getSharedPreferences(prefInfo, Context.MODE_PRIVATE)

    override var deliveryManId: String
        get() = mPrefs.getString(PREF_KEY_DRIVERID,"").toString()
        set(deliveryManId) = mPrefs.edit().putString(PREF_KEY_DRIVERID, deliveryManId).apply()

    override var invoice: String
        get() = mPrefs.getString(PREF_KEY_INVOICE,"").toString()
        set(invoice) = mPrefs.edit().putString(PREF_KEY_INVOICE, invoice).apply()

    override var customerName: String
        get() = mPrefs.getString(PREF_KEY_CUSTOMER_NAME,"").toString()
        set(customerName) = mPrefs.edit().putString(PREF_KEY_CUSTOMER_NAME, customerName).apply()

    override var customerAddress: String
        get() = mPrefs.getString(PREF_KEY_CUSTOMER_ADDRESS,"").toString()
        set(customerAddress) = mPrefs.edit().putString(PREF_KEY_CUSTOMER_ADDRESS, customerAddress).apply()

    override var customerPhone: String
        get() = mPrefs.getString(PREF_KEY_CUSTOMER_PHONE,"").toString()
        set(customerPhone) = mPrefs.edit().putString(PREF_KEY_CUSTOMER_PHONE, customerPhone).apply()

    override var receiverPhone: String
        get() = mPrefs.getString(PREF_KEY_RECEIVER_PHONE,"").toString()
        set(receiverPhone) = mPrefs.edit().putString(PREF_KEY_RECEIVER_PHONE, receiverPhone).apply()

    override var toTime: String
        get() = mPrefs.getString(PREF_KEY_TO_TIME,"").toString()
        set(toTime) = mPrefs.edit().putString(PREF_KEY_TO_TIME, toTime).apply()

    override var fromTime: String
        get() = mPrefs.getString(PREF_KEY_FROM_TIME,"").toString()
        set(fromTime) = mPrefs.edit().putString(PREF_KEY_FROM_TIME, fromTime).apply()



    companion object {
        private val PREF_KEY_DRIVERID = "PREF_KEY_DRIVERID"
        private val PREF_KEY_INVOICE = "PREF_KEY_INVOICE"
        private val PREF_KEY_CUSTOMER_NAME = "PREF_KEY_CUSTOMER_NAME"
        private val PREF_KEY_CUSTOMER_ADDRESS = "PREF_KEY_CUSTOMER_ADDRESS"
        private val PREF_KEY_CUSTOMER_PHONE = "PREF_KEY_CUSTOMER_PHONE"
        private val PREF_KEY_RECEIVER_PHONE = "PREF_KEY_RECEIVER_PHONE"
        private val PREF_KEY_TO_TIME = "PREF_KEY_TO_TIME"
        private val PREF_KEY_FROM_TIME = "PREF_KEY_FROM_TIME"
    }
}