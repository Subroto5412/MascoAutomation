package com.bd.mascogroup.automation.data.local.db


import com.bd.mascogroup.automation.data.model.db.*
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

    override fun insertSearchItem(searchlist: Searchlist): Observable<Boolean> {
        return Observable.fromCallable {
            mAppDatabase.searchlistDao().insert(searchlist)
            true
        }
    }

    override fun getSearchItemList(): Observable<List<Searchlist>> {
        return Observable.fromCallable{
            mAppDatabase.searchlistDao().loadAlSearchlist()
        }
    }

    override fun deleteAllSearchlists(): Observable<Boolean> {
        return Observable.fromCallable{
            mAppDatabase.searchlistDao().deleteAllSearchlist()
            true
        }
    }

    override fun getSearchByActivityName(ActivityName: String): Observable<List<Searchlist>> {
        return Observable.fromCallable{
            mAppDatabase.searchlistDao().findByActivityName(ActivityName)
        }
    }

    override fun getSearchByModuleAndActivityName(ActivityName: String, ModuleName: String): Observable<List<Searchlist>> {
        return Observable.fromCallable {
            mAppDatabase.searchlistDao().findByActivityNameModuleName(ActivityName, ModuleName)
        }
    }

    override fun insertLeaveApproval(leaveapprovallist: Leaveapprovallist): Observable<Boolean> {
        return Observable.fromCallable {
            mAppDatabase.leaveapprovallistDao().insert(leaveapprovallist)
            true
        }
    }

    override fun deleteAllLeaveApprovalData(): Observable<Boolean> {
        return Observable.fromCallable{
            mAppDatabase.leaveapprovallistDao().deleteAllLeaveApprovalList()
            true
        }
    }

    override fun updateLeaveApprovalStatus(status: String, empCode: String): Observable<Boolean> {
        return Observable.fromCallable {
            mAppDatabase.leaveapprovallistDao().updateLeaveApprovalStatus(status,empCode)
            true
        }
    }

    override fun loadAllLeaveApproval(): Observable<List<Leaveapprovallist>> {
        return Observable.fromCallable {
            mAppDatabase.leaveapprovallistDao().loadAllLeaveApprovalList()
        }
    }

    override fun getLeaveApprovalByStatus(status: String): Observable<List<Leaveapprovallist>> {
        return Observable.fromCallable {
            mAppDatabase.leaveapprovallistDao().loadLeaveApprovalListByStatus(status)
        }
    }
}