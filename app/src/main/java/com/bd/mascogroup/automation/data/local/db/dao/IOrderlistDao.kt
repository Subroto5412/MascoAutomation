package com.bd.mascogroup.automation.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bd.mascogroup.automation.data.model.db.Orderlist
import com.bd.mascogroup.automation.data.model.db.User

@Dao
interface IOrderlistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(orderlist: Orderlist)

    @Query("SELECT * FROM orderlists")
    fun loadAllOrder(): List<Orderlist>

    @Query("DELETE FROM orderlists")
    fun deleteAllOrderlists()

    @Query("SELECT * FROM orderlists WHERE invoice LIKE :invoice")
    fun findByOrderInvoice(invoice: String): List<Orderlist>

}