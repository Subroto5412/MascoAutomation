package com.bd.mascogroup.automation.data.local.db


import com.bd.mascogroup.automation.data.model.db.Orderlist
import com.bd.mascogroup.automation.data.model.db.Productlist
import com.bd.mascogroup.automation.data.model.db.User
import io.reactivex.Observable
import javax.inject.Inject

class AppDbHelper @Inject constructor(private val mAppDatabase: AppDatabase) : IDbHelper{
//    override fun insertUser(user: User): Observable<Boolean> {
//        return Observable.fromCallable {
//            mAppDatabase.userDao().insert(user)
//            true
//        }
//    }

    override fun insertUser(user: User): Observable<Boolean> {
        return Observable.fromCallable {
            mAppDatabase.userDao().insert(user)
            true
        }
    }

    override fun getUser(): Observable<List<User>> {
        return Observable.fromCallable{
            mAppDatabase.userDao().loadAll()
        }
    }

    override fun insertOrder(orderlist: Orderlist): Observable<Boolean> {
        return Observable.fromCallable {
            mAppDatabase.orderlistDao().insert(orderlist)
            true
        }
    }

    override fun getOrder(): Observable<List<Orderlist>> {
        return Observable.fromCallable{
            mAppDatabase.orderlistDao().loadAllOrder()
        }
    }

    override fun deleteAllOrderlists(): Observable<Boolean> {
        return Observable.fromCallable {
            mAppDatabase.orderlistDao().deleteAllOrderlists()
            true
        }
    }

    override fun getByOrderInvoice(invoice: String): Observable<List<Orderlist>> {
        return Observable.fromCallable{
            mAppDatabase.orderlistDao().findByOrderInvoice(invoice)
        }
    }

    override fun insertProduct(productlist: Productlist): Observable<Boolean> {
        return Observable.fromCallable {
            mAppDatabase.productlistDao().insert(productlist)
            true
        }
    }

    override fun deleteAllProductlistlists(): Observable<Boolean> {
        return Observable.fromCallable {
            mAppDatabase.productlistDao().deleteAllProductlists()
            true
        }
    }

    override fun getByProductlistInvoice(invoice: String): Observable<List<Productlist>> {
        return Observable.fromCallable{
            mAppDatabase.productlistDao().findByProductInvoice(invoice)
        }
    }
}