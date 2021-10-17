package com.bd.mascogroup.automation.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "empname")
class Empname {

    @PrimaryKey(autoGenerate = true)
    @Expose
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var id: Int = 0

    @Expose
    @SerializedName("emP_CODE")
    @ColumnInfo(name = "emP_CODE")
    lateinit var emP_CODE: String

    @Expose
    @SerializedName("emp_full")
    @ColumnInfo(name = "emp_full")
    lateinit var emp_full: String

    @Expose
    @SerializedName("unitNo")
    @ColumnInfo(name = "unitNo")
    var unitNo: Int = 0
}