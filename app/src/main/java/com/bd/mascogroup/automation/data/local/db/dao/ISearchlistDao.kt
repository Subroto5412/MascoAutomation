package com.bd.mascogroup.automation.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bd.mascogroup.automation.data.model.db.Searchlist

@Dao
interface ISearchlistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(searchlist: Searchlist)

    @Query("SELECT * FROM searchlists")
    fun loadAlSearchlist(): List<Searchlist>

    @Query("DELETE FROM searchlists")
    fun deleteAllSearchlist()

    @Query("SELECT * FROM searchlists WHERE search_name LIKE '%' || :search_name || '%'")
    fun findByActivityName(search_name: String): List<Searchlist>

    @Query("SELECT * FROM searchlists WHERE search_name LIKE :search_name and module_name LIKE :module_name")
    fun findByActivityNameModuleName(search_name: String, module_name: String): List<Searchlist>
}