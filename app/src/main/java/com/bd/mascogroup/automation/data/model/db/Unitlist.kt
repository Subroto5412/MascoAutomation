package com.bd.mascogroup.automation.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "unitlist")
class Unitlist {

    @PrimaryKey(autoGenerate = true)
    @Expose
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var id: Int = 0

    @Expose
    @SerializedName("unitNo")
    @ColumnInfo(name = "unitNo")
    var unitNo: Int = 0

    @Expose
    @SerializedName("unitName")
    @ColumnInfo(name = "unitName")
    lateinit var unitName: String
}