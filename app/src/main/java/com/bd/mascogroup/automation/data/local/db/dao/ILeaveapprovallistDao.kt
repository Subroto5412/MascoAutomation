package com.bd.mascogroup.automation.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bd.mascogroup.automation.data.model.db.Leaveapprovallist

@Dao
interface ILeaveapprovallistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(leaveapprovallist: Leaveapprovallist)

    @Query("SELECT * FROM leaveapprovallist")
    fun loadAllLeaveApprovalList(): List<Leaveapprovallist>

    @Query("SELECT * FROM leaveapprovallist WHERE status LIKE :status")
    fun loadLeaveApprovalListByStatus(status: String): List<Leaveapprovallist>

    @Query("DELETE FROM leaveapprovallist")
    fun deleteAllLeaveApprovalList()

    @Query("UPDATE leaveapprovallist SET status=:status WHERE empCode LIKE :empCode")
    fun updateLeaveApprovalStatus(status: String, empCode: String)
}