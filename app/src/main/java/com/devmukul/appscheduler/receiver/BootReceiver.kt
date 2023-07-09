package com.devmukul.appscheduler.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val invoke: Any = VMRunner.invoke("vIFnKCKXctyA7ATR", arrayOf<Any>(this, context, intent))
    }
}