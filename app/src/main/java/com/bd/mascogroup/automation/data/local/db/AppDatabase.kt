package com.bd.mascogroup.automation.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bd.mascogroup.automation.data.local.db.dao.*
import com.bd.mascogroup.automation.data.model.db.*
import com.bd.mascogroup.automation.utils.AppConstants.DB_VERSION

@Database(entities = [User::class, Orderlist::class, Productlist::class, Searchlist::class, Leaveapprovallist::class, Empname::class, Unitlist::class], version = DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): IUserDao
    abstract fun orderlistDao(): IOrderlistDao
    abstract fun productlistDao(): IProductlistDao
    abstract fun searchlistDao(): ISearchlistDao
    abstract fun leaveapprovallistDao(): ILeaveapprovallistDao
    abstract fun empnameDao(): IEmpnameDao
    abstract fun unitlistDao(): IUnitlistDao
}