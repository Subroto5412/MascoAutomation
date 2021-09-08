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

    override var mobile: String
        get() = mPrefs.getString(PREF_KEY_PHONE,"").toString()
        set(mobile) = mPrefs.edit().putString(PREF_KEY_PHONE, mobile).apply()

    override var empId: String
        get() = mPrefs.getString(PREF_KEY_EMPId,"").toString()
        set(empId) = mPrefs.edit().putString(PREF_KEY_EMPId, empId).apply()

    override var empCode: String
        get() = mPrefs.getString(PREF_KEY_EMPCODE,"").toString()
        set(empCode) = mPrefs.edit().putString(PREF_KEY_EMPCODE, empCode).apply()



    companion object {
        private val PREF_KEY_DRIVERID = "PREF_KEY_DRIVERID"
        private val PREF_KEY_INVOICE = "PREF_KEY_INVOICE"
        private val PREF_KEY_CUSTOMER_NAME = "PREF_KEY_CUSTOMER_NAME"
        private val PREF_KEY_CUSTOMER_ADDRESS = "PREF_KEY_CUSTOMER_ADDRESS"
        private val PREF_KEY_CUSTOMER_PHONE = "PREF_KEY_CUSTOMER_PHONE"
        private val PREF_KEY_PHONE = "PREF_KEY_PHONE"
        private val PREF_KEY_EMPId = "PREF_KEY_EMPId"
        private val PREF_KEY_EMPCODE = "PREF_KEY_EMPCODE"
    }
}