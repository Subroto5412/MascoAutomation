package com.bd.mascogroup.automation.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import com.bd.mascogroup.automation.di.PreferenceInfo
import javax.inject.Inject

class PreferencesHelper @Inject constructor(context: Context, @PreferenceInfo prefInfo: String):IPreferencesHelper{

    private val mPrefs: SharedPreferences =
            context.getSharedPreferences(prefInfo, Context.MODE_PRIVATE)

    override var customerPic: String
        get() = mPrefs.getString(PREF_KEY_CUSTOMER_PIC,"").toString()
        set(customerPic) = mPrefs.edit().putString(PREF_KEY_CUSTOMER_PIC, customerPic).apply()

    override var unitName: String
        get() = mPrefs.getString(PREF_KEY_UNIT_NAME,"").toString()
        set(unitName) = mPrefs.edit().putString(PREF_KEY_UNIT_NAME, unitName).apply()

    override var customerName: String
        get() = mPrefs.getString(PREF_KEY_CUSTOMER_NAME,"").toString()
        set(customerName) = mPrefs.edit().putString(PREF_KEY_CUSTOMER_NAME, customerName).apply()

    override var rememberMe: String
        get() = mPrefs.getString(PREF_KEY_REMEMBER_ME,"").toString()
        set(rememberMe) = mPrefs.edit().putString(PREF_KEY_REMEMBER_ME, rememberMe).apply()

    override var password: String
        get() = mPrefs.getString(PREF_KEY_PASSWORD,"").toString()
        set(password) = mPrefs.edit().putString(PREF_KEY_PASSWORD, password).apply()

    override var accessToken: String
        get() = mPrefs.getString(PREF_KEY_ACCESS_TOKEN,"").toString()
        set(accessToken) = mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()

    override var refreshToken: String
        get() = mPrefs.getString(PREF_KEY_REFRESH_TOKEN,"").toString()
        set(refreshToken) = mPrefs.edit().putString(PREF_KEY_REFRESH_TOKEN, refreshToken).apply()

    override var mobile: String
        get() = mPrefs.getString(PREF_KEY_PHONE,"").toString()
        set(mobile) = mPrefs.edit().putString(PREF_KEY_PHONE, mobile).apply()

    override var empId: String
        get() = mPrefs.getString(PREF_KEY_EMPId,"").toString()
        set(empId) = mPrefs.edit().putString(PREF_KEY_EMPId, empId).apply()

    override var empCode: String
        get() = mPrefs.getString(PREF_KEY_EMPCODE,"").toString()
        set(empCode) = mPrefs.edit().putString(PREF_KEY_EMPCODE, empCode).apply()

    override var saveEmpCode: String
        get() = mPrefs.getString(PREF_KEY_SAVE_EMPCODE,"").toString()
        set(saveEmpCode) = mPrefs.edit().putString(PREF_KEY_SAVE_EMPCODE, saveEmpCode).apply()

    override var HRModule: String
        get() = mPrefs.getString(PREF_KEY_HR_MODULE,"").toString()
        set(HRModule) = mPrefs.edit().putString(PREF_KEY_HR_MODULE, HRModule).apply()

    override var dailyAttendance: String
        get() = mPrefs.getString(PREF_KEY_DAILY_ATTENDANCE,"").toString()
        set(dailyAttendance) = mPrefs.edit().putString(PREF_KEY_DAILY_ATTENDANCE, dailyAttendance).apply()

    override var leaveHistory: String
        get() = mPrefs.getString(PREF_KEY_LEAVE_HISTORY,"").toString()
        set(leaveHistory) = mPrefs.edit().putString(PREF_KEY_LEAVE_HISTORY, leaveHistory).apply()

    override var taxHistory: String
        get() = mPrefs.getString(PREF_KEY_TAX_HISTORY,"").toString()
        set(taxHistory) = mPrefs.edit().putString(PREF_KEY_TAX_HISTORY, taxHistory).apply()

    override var GPMSModule: String
        get() = mPrefs.getString(PREF_KEY_GPMSMOUDLE,"").toString()
        set(GPMSModule) = mPrefs.edit().putString(PREF_KEY_GPMSMOUDLE, GPMSModule).apply()

    override var buyerWiseProductionData: String
        get() = mPrefs.getString(PREF_KEY_BUYER_WISE_PRODUCT_DATA,"").toString()
        set(buyerWiseProductionData) = mPrefs.edit().putString(PREF_KEY_BUYER_WISE_PRODUCT_DATA, buyerWiseProductionData).apply()

    override var hourlyProductionData: String
        get() = mPrefs.getString(PREF_KEY_HOURLY_PRODUCT_DATA,"").toString()
        set(hourlyProductionData) = mPrefs.edit().putString(PREF_KEY_HOURLY_PRODUCT_DATA, hourlyProductionData).apply()

    override var hourlyProductionDetails: String
        get() = mPrefs.getString(PREF_KEY_HOURLY_PRODUCTION_DETAILS,"").toString()
        set(hourlyProductionDetails) = mPrefs.edit().putString(PREF_KEY_HOURLY_PRODUCTION_DETAILS, hourlyProductionDetails).apply()

    override var lineWiseProduction: String
        get() = mPrefs.getString(PREF_KEY_LINE_PRODUCT_DATA,"").toString()
        set(lineWiseProduction) = mPrefs.edit().putString(PREF_KEY_LINE_PRODUCT_DATA, lineWiseProduction).apply()


    companion object {
        private val PREF_KEY_CUSTOMER_PIC = "PREF_KEY_CUSTOMER_PIC"
        private val PREF_KEY_UNIT_NAME = "PREF_KEY_UNIT_NAME"
        private val PREF_KEY_CUSTOMER_NAME = "PREF_KEY_CUSTOMER_NAME"
        private val PREF_KEY_REMEMBER_ME = "PREF_KEY_REMEMBER_ME"
        private val PREF_KEY_PASSWORD = "PREF_KEY_PASSWORD"
        private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        private val PREF_KEY_REFRESH_TOKEN = "PREF_KEY_REFRESH_TOKEN"
        private val PREF_KEY_PHONE = "PREF_KEY_PHONE"
        private val PREF_KEY_EMPId = "PREF_KEY_EMPId"
        private val PREF_KEY_EMPCODE = "PREF_KEY_EMPCODE"
        private val PREF_KEY_SAVE_EMPCODE = "PREF_KEY_SAVE_EMPCODE"
        private val PREF_KEY_HR_MODULE = "PREF_KEY_HR_MODULE"
        private val PREF_KEY_DAILY_ATTENDANCE = "PREF_KEY_DAILY_ATTENDANCE"
        private val PREF_KEY_LEAVE_HISTORY = "PREF_KEY_LEAVE_HISTORY"
        private val PREF_KEY_TAX_HISTORY = "PREF_KEY_TAX_HISTORY"

        private val PREF_KEY_GPMSMOUDLE = "PREF_KEY_GPMSMOUDLE"
        private val PREF_KEY_BUYER_WISE_PRODUCT_DATA = "PREF_KEY_BUYER_WISE_PRODUCT_DATA"
        private val PREF_KEY_HOURLY_PRODUCT_DATA = "PREF_KEY_HOURLY_PRODUCT_DATA"
        private val PREF_KEY_HOURLY_PRODUCTION_DETAILS = "PREF_KEY_HOURLY_PRODUCTION_DETAILS"
        private val PREF_KEY_LINE_PRODUCT_DATA = "PREF_KEY_LINE_PRODUCT_DATA"
    }
}