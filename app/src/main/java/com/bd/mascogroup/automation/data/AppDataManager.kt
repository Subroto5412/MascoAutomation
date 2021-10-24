package com.bd.mascogroup.automation.data

import android.content.Context
import com.google.gson.Gson
import io.reactivex.Observable
import com.bd.mascogroup.automation.data.local.db.IDbHelper
import com.bd.mascogroup.automation.data.local.prefs.IPreferencesHelper
import com.bd.mascogroup.automation.data.model.db.*
import javax.inject.Inject

class AppDataManager @Inject constructor(
    private val mContext: Context,
    private val mDbHelper: IDbHelper,
    private val mPreferencesHelper: IPreferencesHelper,
    private val mGson: Gson,
) : IDataManager {
    override var customerPic: String
        get() = mPreferencesHelper.customerPic
        set(customerPic) {
            mPreferencesHelper.customerPic = customerPic
        }
    override var customerName: String
        get() = mPreferencesHelper.customerName
        set(customerName) {
            mPreferencesHelper.customerName = customerName
        }
    override var rememberMe: String
        get() = mPreferencesHelper.rememberMe
        set(rememberMe) {
            mPreferencesHelper.rememberMe = rememberMe
        }

    override var password: String
        get() = mPreferencesHelper.password
        set(password) {
            mPreferencesHelper.password = password
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

    override var saveEmpCode: String
        get() = mPreferencesHelper.saveEmpCode
        set(saveEmpCode) {
            mPreferencesHelper.saveEmpCode = saveEmpCode
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

    override var GPMSModule: String
        get() = mPreferencesHelper.GPMSModule
        set(GPMSModule) {
            mPreferencesHelper.GPMSModule = GPMSModule
        }
    override var buyerWiseProductionData: String
        get() = mPreferencesHelper.buyerWiseProductionData
        set(buyerWiseProductionData) {
            mPreferencesHelper.buyerWiseProductionData = buyerWiseProductionData
        }
    override var hourlyProductionData: String
        get() = mPreferencesHelper.hourlyProductionData
        set(hourlyProductionData) {
            mPreferencesHelper.hourlyProductionData = hourlyProductionData
        }
    override var hourlyProductionDetails: String
        get() = mPreferencesHelper.hourlyProductionDetails
        set(hourlyProductionDetails) {
            mPreferencesHelper.hourlyProductionDetails = hourlyProductionDetails
        }
    override var lineWiseProduction: String
        get() = mPreferencesHelper.lineWiseProduction
        set(lineWiseProduction) {
            mPreferencesHelper.lineWiseProduction = lineWiseProduction
        }

    override var unitName: String
        get() = mPreferencesHelper.unitName
        set(unitName) {
            mPreferencesHelper.unitName = unitName
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

    override fun insertSearchItem(searchlist: Searchlist): Observable<Boolean> {
        return  mDbHelper.insertSearchItem(searchlist)
    }

    override fun getSearchItemList(): Observable<List<Searchlist>> {
        return mDbHelper.getSearchItemList()
    }

    override fun deleteAllSearchlists(): Observable<Boolean> {
        return  mDbHelper.deleteAllSearchlists()
    }

    override fun getSearchByActivityName(ActivityName: String): Observable<List<Searchlist>> {
        return  mDbHelper.getSearchByActivityName(ActivityName)
    }

    override fun getSearchByModuleAndActivityName(ActivityName: String, ModuleName: String): Observable<List<Searchlist>> {
        return  mDbHelper.getSearchByModuleAndActivityName(ActivityName, ModuleName)
    }

    override fun insertLeaveApproval(leaveapprovallist: Leaveapprovallist): Observable<Boolean> {
        return mDbHelper.insertLeaveApproval(leaveapprovallist)
    }

    override fun deleteAllLeaveApprovalData(): Observable<Boolean> {
        return  mDbHelper.deleteAllLeaveApprovalData()
    }

    override fun updateLeaveApprovalStatus(status: String, empCode: String): Observable<Boolean> {
       return  mDbHelper.updateLeaveApprovalStatus(status, empCode)
    }

    override fun loadAllLeaveApproval(): Observable<List<Leaveapprovallist>> {
        return  mDbHelper.loadAllLeaveApproval()
    }

    override fun getLeaveApprovalByStatus(status: String): Observable<List<Leaveapprovallist>> {
        return  mDbHelper.getLeaveApprovalByStatus(status)
    }

    override fun insertEmpNameItem(empname: Empname): Observable<Boolean> {
        return mDbHelper.insertEmpNameItem(empname)
    }

    override fun deleteAllEmpNameLists(): Observable<Boolean> {
        return mDbHelper.deleteAllEmpNameLists()
    }

    override fun getSearchByEmpName(Name: String, unitNo: String): Observable<List<Empname>> {
        return mDbHelper.getSearchByEmpName(Name, unitNo)
    }

    override fun getSearchByAllEmpName(Name: String): Observable<List<Empname>> {
        return mDbHelper.getSearchByAllEmpName(Name)
    }

    override fun getSearchCodeByEmpName(Name: String): Observable<List<Empname>> {
        return mDbHelper.getSearchCodeByEmpName(Name)
    }

    override fun insertUnitItem(unitlist: Unitlist): Observable<Boolean> {
        return mDbHelper.insertUnitItem(unitlist)
    }

    override fun deleteAllUnitLists(): Observable<Boolean> {
        return  mDbHelper.deleteAllUnitLists()
    }

    override fun getSearchByUnit(unitName: String): Observable<List<Unitlist>> {
        return  mDbHelper.getSearchByUnit(unitName)
    }

    override fun getSearchUnitNoByUnitName(unitName: String): Observable<List<Unitlist>> {
        return  mDbHelper.getSearchUnitNoByUnitName(unitName)
    }

}