package com.bd.mascogroup.automation.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bd.mascogroup.automation.data.model.db.Unitlist

@Dao
interface IUnitlistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(unitlist: Unitlist)

    @Query("DELETE FROM Unitlist")
    fun deleteAllUnitList()

    @Query("SELECT * FROM Unitlist WHERE unitName LIKE '%' || :unitName || '%'")
    fun findByUnitName(unitName: String): List<Unitlist>


    @Query("SELECT * FROM Unitlist WHERE unitName LIKE :unitName")
    fun findCodeUnitName(unitName: String): List<Unitlist>
}
