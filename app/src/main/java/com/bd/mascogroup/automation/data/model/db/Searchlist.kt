package com.bd.mascogroup.automation.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "searchlists")
class Searchlist {

    @PrimaryKey(autoGenerate = true)
    @Expose
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var id: Int = 0

    @Expose
    @SerializedName("activity_name")
    @ColumnInfo(name = "activity_name")
    lateinit var activity_name: String

    @Expose
    @SerializedName("search_name")
    @ColumnInfo(name = "search_name")
    lateinit var search_name: String

    @Expose
    @SerializedName("module_name")
    @ColumnInfo(name = "module_name")
    lateinit var module_name: String
}