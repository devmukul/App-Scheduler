package com.devmukul.appscheduler.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TimeChangedReceiver : BroadcastReceiver() {
    private val mTemplateDao: TemplateDAO? = null
    override fun onReceive(context: Context, intent: Intent) {
        val invoke: Any = VMRunner.invoke("WVuWSJJCmiPSakdQ", arrayOf<Any>(this, context, intent))
    }

    companion object {
        const val TAG = "TimeChangedReceiver"
    }
}