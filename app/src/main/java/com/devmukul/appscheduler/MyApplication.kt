package com.devmukul.appscheduler

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.*
import androidx.multidex.MultiDexApplication
import com.devmukul.appscheduler.database.AppDatabase
import java.util.concurrent.TimeUnit

class MyApplication : MultiDexApplication(), LifecycleObserver {


    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        instance = this
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        //Database
        mDatabase = AppDatabase.getAppDatabase(this)

    }

    companion object {
        private val TAG = MyApplication::class.java.simpleName
        lateinit var instance: MyApplication
            private set

        lateinit var mDatabase: AppDatabase
            private set

        fun getAppContext(): MyApplication {
            return instance
        }
    }

}