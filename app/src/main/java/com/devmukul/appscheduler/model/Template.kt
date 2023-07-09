package com.devmukul.appscheduler.model

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import java.io.Serializable

@Keep
@Entity(tableName = "Templates", primaryKeys = ["_id"])
data class Template(
    @ColumnInfo(defaultValue = "")
    var _id: Long ,

    @ColumnInfo(defaultValue = "")
    var description: String ,

    @ColumnInfo(defaultValue = "")
    var enabled: Int ,

    @ColumnInfo(defaultValue = "")
    var name: String ,

    @ColumnInfo(defaultValue = "")
    var packageName: String ,

    @ColumnInfo(defaultValue = "")
    var schedulingConfirmTask: Int ,

    @ColumnInfo(defaultValue = "")
    var schedulingDateEnd: Long ,

    @ColumnInfo(defaultValue = "")
    var schedulingDateStart: Long ,

    @ColumnInfo(defaultValue = "")
    var schedulingId: Int ,

    @ColumnInfo(defaultValue = "")
    var schedulingInterval: Int ,

    @ColumnInfo(defaultValue = "")
    var schedulingIntervalMultiplier: Long ,

    @ColumnInfo(defaultValue = "")
    var schedulingRepeat: Int ,

    @ColumnInfo(defaultValue = "")
    var schedulingStatus: Int ,

    @ColumnInfo(defaultValue = "")
    var text: Int

): Serializable
