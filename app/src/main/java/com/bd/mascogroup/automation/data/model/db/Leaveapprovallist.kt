package com.bd.mascogroup.automation.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "leaveapprovallist")
class Leaveapprovallist {

    @PrimaryKey(autoGenerate = true)
    @Expose
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var id: Int = 0

    @Expose
    @SerializedName("applyNo")
    @ColumnInfo(name = "applyNo")
     var applyNo: Int = 0

    @Expose
    @SerializedName("empCode")
    @ColumnInfo(name = "empCode")
    lateinit var empCode: String

    @Expose
    @SerializedName("empName")
    @ColumnInfo(name = "empName")
    lateinit var empName: String

    @Expose
    @SerializedName("designation")
    @ColumnInfo(name = "designation")
    lateinit var designation: String

    @Expose
    @SerializedName("applyFromDate")
    @ColumnInfo(name = "applyFromDate")
    lateinit var applyFromDate: String

    @Expose
    @SerializedName("applyToDate")
    @ColumnInfo(name = "applyToDate")
    lateinit var applyToDate: String

    @Expose
    @SerializedName("applyDays")
    @ColumnInfo(name = "applyDays")
    lateinit var applyDays: String

    @Expose
    @SerializedName("leaveNo")
    @ColumnInfo(name = "leaveNo")
    var leaveNo: Int = 0

    @Expose
    @SerializedName("leaveType")
    @ColumnInfo(name = "leaveType")
    lateinit var leaveType: String

    @Expose
    @SerializedName("leaveMax")
    @ColumnInfo(name = "leaveMax")
    var leaveMax: Int = 0

    @Expose
    @SerializedName("leaveAvail")
    @ColumnInfo(name = "leaveAvail")
    var leaveAvail: Int = 0

    @Expose
    @SerializedName("status")
    @ColumnInfo(name = "status")
    lateinit var status: String
}