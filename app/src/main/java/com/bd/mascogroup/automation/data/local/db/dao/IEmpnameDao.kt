package com.bd.mascogroup.automation.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bd.mascogroup.automation.data.model.db.Empname

@Dao
interface IEmpnameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(empname: Empname)

    @Query("DELETE FROM empname")
    fun deleteAllEmpNameList()

    @Query("SELECT * FROM empname WHERE unitNo LIKE :unitNo and emp_full LIKE '%' || :emp_full || '%'")
    fun findByEmpName(emp_full: String, unitNo: String): List<Empname>

    @Query("SELECT * FROM empname WHERE emp_full LIKE '%' || :emp_full || '%'")
    fun findByAll(emp_full: String): List<Empname>

    @Query("SELECT * FROM empname WHERE emp_full LIKE :emp_full")
    fun findCodeByEmpName(emp_full: String): List<Empname>

}