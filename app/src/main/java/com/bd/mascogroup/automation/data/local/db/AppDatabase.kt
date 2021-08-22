package com.bd.mascogroup.automation.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bd.mascogroup.automation.data.local.db.dao.IOrderlistDao
import com.bd.mascogroup.automation.data.local.db.dao.IProductlistDao
import com.bd.mascogroup.automation.data.local.db.dao.IUserDao
import com.bd.mascogroup.automation.data.model.db.Orderlist
import com.bd.mascogroup.automation.data.model.db.Productlist
import com.bd.mascogroup.automation.data.model.db.User
import com.bd.mascogroup.automation.utils.AppConstants.DB_VERSION

@Database(entities = [User::class, Orderlist::class, Productlist::class], version = DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): IUserDao
    abstract fun orderlistDao(): IOrderlistDao
    abstract fun productlistDao(): IProductlistDao
}