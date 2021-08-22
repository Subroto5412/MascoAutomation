package com.bd.mascogroup.automation.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "productlists")
class Productlist {

    @PrimaryKey(autoGenerate = true)
    @Expose
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var id: Int = 0

    @Expose
    @SerializedName("invoice")
    @ColumnInfo(name = "invoice")
    lateinit var invoice: String

    @Expose
    @SerializedName("item")
    @ColumnInfo(name = "item")
    lateinit var item: String

    @Expose
    @SerializedName("discount")
    @ColumnInfo(name = "discount")
    lateinit var discount: String

    @Expose
    @SerializedName("issue_qty")
    @ColumnInfo(name = "issue_qty")
    lateinit var issue_qty: String

    @Expose
    @SerializedName("total_amount")
    @ColumnInfo(name = "total_amount")
    lateinit var total_amount: String

    @Expose
    @SerializedName("total_due")
    @ColumnInfo(name = "total_due")
    lateinit var total_due: String
}