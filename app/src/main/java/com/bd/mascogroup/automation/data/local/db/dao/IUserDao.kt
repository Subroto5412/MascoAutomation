package com.bd.mascogroup.automation.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bd.mascogroup.automation.data.model.db.User

@Dao
interface IUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM users")
    fun loadAll(): List<User>
}