package com.bd.mascogroup.automation.data.local.db

import com.bd.mascogroup.automation.data.model.db.*
import io.reactivex.Observable

interface IDbHelper {
    fun insertUser(user: User): Observable<Boolean>
    fun getUser(): Observable<List<User>>

    fun insertOrder(orderlist: Orderlist): Observable<Boolean>
    fun getOrder(): Observable<List<Orderlist>>

    fun deleteAllOrderlists(): Observable<Boolean>
    fun getByOrderInvoice(invoice: String):  Observable<List<Orderlist>>


    fun insertProduct(productlist: Productlist): Observable<Boolean>
//    fun getOrder(): Observable<List<Orderlist>>

    fun deleteAllProductlistlists(): Observable<Boolean>
    fun getByProductlistInvoice(invoice: String):  Observable<List<Productlist>>


    //Search
    fun insertSearchItem(searchlist: Searchlist): Observable<Boolean>
    fun getSearchItemList(): Observable<List<Searchlist>>
    fun deleteAllSearchlists(): Observable<Boolean>
    fun getSearchByActivityName(ActivityName: String):  Observable<List<Searchlist>>
    fun getSearchByModuleAndActivityName(ActivityName: String, ModuleName: String):  Observable<List<Searchlist>>

    //leave approval list
    fun insertLeaveApproval(leaveapprovallist: Leaveapprovallist): Observable<Boolean>
    fun deleteAllLeaveApprovalData(): Observable<Boolean>
    fun updateLeaveApprovalStatus(status: String, empCode: String):Observable<Boolean>
    fun loadAllLeaveApproval():Observable<List<Leaveapprovallist>>
    fun getLeaveApprovalByStatus(status: String):  Observable<List<Leaveapprovallist>>

    //EmpName
    fun insertEmpNameItem(empname: Empname): Observable<Boolean>
    fun deleteAllEmpNameLists(): Observable<Boolean>
    fun getSearchByEmpName(Name: String, unitNo:String):  Observable<List<Empname>>
    fun getSearchByAllEmpName(Name: String):  Observable<List<Empname>>
    fun getSearchCodeByEmpName(Name: String):  Observable<List<Empname>>
}