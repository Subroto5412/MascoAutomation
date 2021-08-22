package com.bd.mascogroup.automation.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "users")
class User {
    @PrimaryKey
    @Expose
    @SerializedName("id")
    @ColumnInfo(name = "id")
     var id: Int = 0

    @Expose
    @SerializedName("email")
    @ColumnInfo(name = "email")
    lateinit var email: String

    @Expose
    @SerializedName("userId")
    @ColumnInfo(name = "userId")
    lateinit var userId: String

    @Expose
    @SerializedName("userName")
    @ColumnInfo(name = "userName")
    lateinit var userName: String

}