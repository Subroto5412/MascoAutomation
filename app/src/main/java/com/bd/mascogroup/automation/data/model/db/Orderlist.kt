package com.bd.mascogroup.automation.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "orderlists")
class Orderlist {
    @PrimaryKey(autoGenerate = true)
    @Expose
    @SerializedName("id")
    @ColumnInfo(name = "id")
     var id: Int = 0

    @Expose
    @SerializedName("deliveryId")
    @ColumnInfo(name = "deliveryId")
    lateinit var deliveryId: String

    @Expose
    @SerializedName("invoice")
    @ColumnInfo(name = "invoice")
    lateinit var invoice: String

    @Expose
    @SerializedName("orderNo")
    @ColumnInfo(name = "orderNo")
    lateinit var orderNo: String

    @Expose
    @SerializedName("orderGenId")
    @ColumnInfo(name = "orderGenId")
    lateinit var orderGenId: String

    @Expose
    @SerializedName("totalAmount")
    @ColumnInfo(name = "totalAmount")
    lateinit var totalAmount: String

    @Expose
    @SerializedName("totalDue")
    @ColumnInfo(name = "totalDue")
    lateinit var totalDue: String

    @Expose
    @SerializedName("customerName")
    @ColumnInfo(name = "customerName")
    lateinit var customerName: String

    @Expose
    @SerializedName("customerId")
    @ColumnInfo(name = "customerId")
    lateinit var customerId: String

    @Expose
    @SerializedName("address")
    @ColumnInfo(name = "address")
    lateinit var address: String

    @Expose
    @SerializedName("fromTime")
    @ColumnInfo(name = "fromTime")
    lateinit var fromTime: String

    @Expose
    @SerializedName("toTime")
    @ColumnInfo(name = "toTime")
    lateinit var toTime: String

    @Expose
    @SerializedName("orderDate")
    @ColumnInfo(name = "orderDate")
    lateinit var orderDate: String

    @Expose
    @SerializedName("deliveryDate")
    @ColumnInfo(name = "deliveryDate")
    lateinit var deliveryDate: String

    @Expose
    @SerializedName("customerMobile")
    @ColumnInfo(name = "customerMobile")
    lateinit var customerMobile: String

    @Expose
    @SerializedName("receiverMobile")
    @ColumnInfo(name = "receiverMobile")
    lateinit var receiverMobile: String

    @Expose
    @SerializedName("late")
    @ColumnInfo(name = "late")
    lateinit var late: String

    @Expose
    @SerializedName("longe")
    @ColumnInfo(name = "longe")
    lateinit var longe: String
}