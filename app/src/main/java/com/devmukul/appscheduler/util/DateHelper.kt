package com.devmukul.appscheduler.util

import android.content.Context
import java.text.DateFormat
import java.util.Calendar
import java.util.Date

object DateHelper {
    fun isValidSchedulingDateStart(j: Long, j2: Long): Boolean {
        return j2 > 0 && j2 > j
    }

    val currentDate: Long
        get() {
            val instance = Calendar.getInstance()
            instance[13] = 0
            instance[14] = 0
            return instance.timeInMillis
        }

    fun getDateTime(context: Context, j: Long, i: Int, i2: Int): String {
        return if (j <= 0) {
            ""
        } else try {
            getDate(context, j, i) + " (" + getTime(j, i2) + ")"
        } catch (unused: Exception) {
            ""
        }
    }

    fun getDate(context: Context, j: Long, i: Int): String {
        return if (j <= 0) {
            ""
        } else try {
            val resources = context.resources
            val instance = Calendar.getInstance()
            val instance2 = Calendar.getInstance()
            instance2.timeInMillis = j
            if (instance2[1] == instance[1] && instance2[6] == instance[6]) {
                resources.getString(C0491R.string.date_today)
            } else DateFormat.getDateInstance(i).format(instance2)
        } catch (unused: Exception) {
            ""
        }
    }

    fun getDateTime(j: Long, i: Int, i2: Int): String {
        return if (j <= 0) {
            ""
        } else try {
            val date = Date()
            date.time = j
            DateFormat.getDateTimeInstance(i, i2).format(date)
        } catch (unused: Exception) {
            ""
        }
    }

    fun getDate(j: Long, i: Int): String {
        return if (j <= 0) {
            ""
        } else try {
            val date = Date()
            date.time = j
            DateFormat.getDateInstance(i).format(date)
        } catch (unused: Exception) {
            ""
        }
    }

    fun getTime(j: Long, i: Int): String {
        return if (j <= 0) {
            ""
        } else try {
            val date = Date()
            date.time = j
            DateFormat.getTimeInstance(i).format(date)
        } catch (unused: Exception) {
            ""
        }
    }

    fun getYearsDifference(j: Long, j2: Long): Long {
        val calendar = getCalendar(j)
        val calendar2 = getCalendar(j2)
        var i = calendar2[1] - calendar[1]
        if (calendar[2] > calendar2[2] || calendar[2] == calendar2[2] && calendar[5] > calendar2[5] || calendar[2] == calendar2[2] && calendar[5] == calendar2[5] && calendar[11] > calendar2[11] || calendar[2] == calendar2[2] && calendar[5] == calendar2[5] && calendar[11] == calendar2[11] && calendar[12] > calendar2[12]) {
            i--
        }
        return i.toLong()
    }

    fun getCalendar(j: Long): Calendar {
        val instance = Calendar.getInstance()
        instance.timeInMillis = j
        return instance
    }
}