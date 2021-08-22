package com.bd.mascogroup.automation.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bd.mascogroup.automation.data.model.db.Orderlist
import com.bd.mascogroup.automation.data.model.db.Productlist

@Dao
interface IProductlistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(productlist: Productlist)

/*    @Query("SELECT * FROM productlists")
    fun loadAllProducts(): List<Productlist>*/

    @Query("DELETE FROM productlists")
    fun deleteAllProductlists()

    @Query("SELECT * FROM productlists WHERE invoice LIKE :invoice")
    fun findByProductInvoice(invoice: String): List<Productlist>
}