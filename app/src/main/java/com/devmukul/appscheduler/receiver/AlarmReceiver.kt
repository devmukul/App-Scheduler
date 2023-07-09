package com.devmukul.appscheduler.receiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import androidx.core.app.NotificationCompat
import com.devmukul.appscheduler.model.Template
import java.util.Calendar

class AlarmReceiver : BroadcastReceiver() {
    private val enabled = 0
    var mContext: Context? = null
    private val mTemplateDao: TemplateDAO? = null
    private val mode = 0
    private val packageName: String? = null
    var res: Resources? = null
    private val schedulingConfirmTask = 0
    private val schedulingDateEnd: Long = 0
    private val schedulingDateStart: Long = 0
    private val schedulingId = 0
    private val schedulingInterval = 0
    private val schedulingIntervalMultiplier: Long = 0
    private val schedulingRepeat = 0
    private val template: Template? = null
    private val text: String? = null

    override fun onReceive(context: Context, intent: Intent) {
        val launchIntent = intent.getParcelableExtra<Intent>("launchIntent")
        launchIntent?.let { context.startActivity(it) }
    }

    companion object {
        const val TAG = "AlarmReceiver"
        var templateId: Long = -1
        fun getAlarmInterval(j: Long, j2: Long): Long {
            return j * j2
        }

        fun getFutureDate(j: Long, j2: Long, j3: Long, j4: Long): Long {
            var j2 = j2
            if (j2 == -1L) {
                j2 = 0
            }
            if (j2 > j) {
                return j2
            }
            val alarmInterval = getAlarmInterval(j3, j4)
            val instance = Calendar.getInstance()
            instance.timeInMillis = j2
            var z = true
            return if (j4 == Constants.ALARM_MANAGER_INTERVAL_YEAR) {
                try {
                    val yearsDifference = (instance[1].toLong() + DateHelper.getYearsDifference(
                        j2,
                        j
                    ) / j3 * j3) as Int
                    instance[1] = yearsDifference
                    if (j >= instance.timeInMillis) {
                        instance[1] = (yearsDifference.toLong() + j3).toInt()
                    }
                    instance.timeInMillis
                } catch (e: Exception) {
                    e.printStackTrace()
                    j + alarmInterval
                }
            } else {
                val j5 = (j - j2) / alarmInterval
                java.lang.Long.signum(j5)
                var j6 = j2 + j5 * alarmInterval
                if (j >= j6) {
                    j6 += alarmInterval
                }
                if (alarmInterval % 86400000 != 0L) {
                    z = false
                }
                if (!z) {
                    return j6
                }
                val instance2 = Calendar.getInstance()
                instance2.timeInMillis = j6
                instance2[11] = instance[11]
                instance2[12] = instance[12]
                instance2.timeInMillis
            }
        }

        fun cancelAlarm(context: Context, i: Int) {
            val pendingIntent =
                getPendingIntent(context, i, Intent(context, AlarmReceiver::class.java))
            (context.getSystemService(NotificationCompat.CATEGORY_ALARM) as AlarmManager).cancel(
                pendingIntent
            )
            pendingIntent.cancel()
        }

        fun cancelAlarms(context: Context, i: Int) {
            cancelAlarm(context, i)
            cancelAlarm(context, -i)
        }

        fun setAlarm(
            context: Context,
            j: Long,
            j2: Long,
            i: Int,
            j3: Long,
            i2: Int,
            i3: Int,
            i4: Int,
            j4: Long
        ) {
            val alarmManager =
                context.getSystemService(NotificationCompat.CATEGORY_ALARM) as AlarmManager
            val intent = Intent(context, AlarmReceiver::class.java)
            intent.putExtra(pyiUgajUWyTK.NpWilKI, j4)
            val pendingIntent = getPendingIntent(context, i4, intent)
            if (i3 == 0) {
                alarmManager[0, j] = pendingIntent
            } else if (i3 == 1) {
                alarmManager.setRepeating(0, j, getAlarmInterval(i.toLong(), j3), pendingIntent)
            }
        }

        fun getPendingIntent(context: Context?, i: Int, intent: Intent?): PendingIntent {
            return PendingIntent.getBroadcast(context, i, intent!!, 201326592)
        }
    }
}