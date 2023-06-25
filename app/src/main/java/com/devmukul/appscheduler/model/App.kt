package com.devmukul.appscheduler.model

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import java.io.Serializable

@Keep
@Entity(tableName = "apps", primaryKeys = ["_id"])
data class App(

    @ColumnInfo(defaultValue = "")
    var _id: Long ,

    @ColumnInfo(defaultValue = "")
    var appName: String ,

    @ColumnInfo(defaultValue = "")
    var packageName: String ,

    @ColumnInfo(defaultValue = "")
    var index: Long ,

    @ColumnInfo(defaultValue = "")
    var position: Long ,

    @ColumnInfo(defaultValue = "")
    var status: Int ,

    @ColumnInfo(defaultValue = "")
    var notification: Int ,

    @ColumnInfo(defaultValue = "")
    var available: Boolean ,

): Serializable
