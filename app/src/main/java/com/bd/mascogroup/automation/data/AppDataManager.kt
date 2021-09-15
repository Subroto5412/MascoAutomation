package com.bd.mascogroup.automation.data

import android.content.Context
import com.google.gson.Gson
import io.reactivex.Observable
import com.bd.mascogroup.automation.data.local.db.IDbHelper
import com.bd.mascogroup.automation.data.local.prefs.IPreferencesHelper
import com.bd.mascogroup.automation.data.model.db.Orderlist
import com.bd.mascogroup.automation.data.model.db.Productlist
import com.bd.mascogroup.automation.data.model.db.User
import javax.inject.Inject

class AppDataManager @Inject constructor(
    private val mContext: Context,
    private val mDbHelper: IDbHelper,
    private val mPreferencesHelper: IPreferencesHelper,
    private val mGson: Gson,
) : IDataManager {
    override var deliveryManId: String
        get() = mPreferencesHelper.deliveryManId
        set(deliveryManId) {
            mPreferencesHelper.deliveryManId = deliveryManId
        }
    override var customerName: String
        get() = mPreferencesHelper.customerName
        set(customerName) {
            mPreferencesHelper.customerName = customerName
        }
    override var SaveTime: String
        get() = mPreferencesHelper.SaveTime
        set(SaveTime) {
            mPreferencesHelper.SaveTime = SaveTime
        }

    override var accessToken: String
        get() = mPreferencesHelper.accessToken
        set(accessToken) {
            mPreferencesHelper.accessToken = accessToken
        }

    override var refreshToken: String
        get() = mPreferencesHelper.refreshToken
        set(refreshToken) {
            mPreferencesHelper.refreshToken = refreshToken
        }

    override var mobile: String
        get() = mPreferencesHelper.mobile
        set(mobile) {
            mPreferencesHelper.mobile = mobile
        }
    override var empId: String
        get() = mPreferencesHelper.empId
        set(empId) {
            mPreferencesHelper.empId = empId
        }

    override var empCode: String
        get() = mPreferencesHelper.empCode
        set(empCode) {
            mPreferencesHelper.empCode = empCode
        }

    override var HRModule: String
        get() = mPreferencesHelper.HRModule
        set(HRModule) {
            mPreferencesHelper.HRModule = HRModule
        }

    override var dailyAttendance: String
        get() = mPreferencesHelper.dailyAttendance
        set(dailyAttendance) {
            mPreferencesHelper.dailyAttendance = dailyAttendance
        }

    override var leaveHistory: String
        get() = mPreferencesHelper.leaveHistory
        set(leaveHistory) {
            mPreferencesHelper.leaveHistory = leaveHistory
        }

    override var taxHistory: String
        get() = mPreferencesHelper.taxHistory
        set(taxHistory) {
            mPreferencesHelper.taxHistory = taxHistory
        }

    override var invoice: String
        get() = mPreferencesHelper.invoice
        set(invoice) {
            mPreferencesHelper.invoice = invoice
        }

    override fun insertUser(user: User): Observable<Boolean> {
        return mDbHelper.insertUser(user)
    }

    override fun getUser(): Observable<List<User>> {
        return mDbHelper.getUser()
    }

    override fun insertOrder(orderlist: Orderlist): Observable<Boolean> {
       return mDbHelper.insertOrder(orderlist)
    }

    override fun getOrder(): Observable<List<Orderlist>> {
        return mDbHelper.getOrder()
    }

    override fun deleteAllOrderlists(): Observable<Boolean> {
       return  mDbHelper.deleteAllOrderlists()
    }

    override fun getByOrderInvoice(invoice: String): Observable<List<Orderlist>> {
     return  mDbHelper.getByOrderInvoice(invoice)
    }

    override fun insertProduct(productlist: Productlist): Observable<Boolean> {
       return mDbHelper.insertProduct(productlist)
    }

    override fun deleteAllProductlistlists(): Observable<Boolean> {
       return  mDbHelper.deleteAllProductlistlists()
    }

    override fun getByProductlistInvoice(invoice: String): Observable<List<Productlist>> {
        return mDbHelper.getByProductlistInvoice(invoice)
    }

/*    override fun doServerLoginApiCall(request: LoginRequest): Single<LoginResponse> {
       return apiService.doServerLoginApiCall(request)
    }*/

}