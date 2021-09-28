package com.bd.mascogroup.automation.data.local.db

import com.bd.mascogroup.automation.data.model.db.Orderlist
import com.bd.mascogroup.automation.data.model.db.Productlist
import com.bd.mascogroup.automation.data.model.db.Searchlist
import io.reactivex.Observable
import com.bd.mascogroup.automation.data.model.db.User

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
}