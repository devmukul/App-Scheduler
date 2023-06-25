package com.devmukul.appscheduler.database

import android.content.Context
import androidx.room.*
import com.devmukul.appscheduler.model.App
import com.devmukul.appscheduler.model.Template

@Database(
    entities = [
        App::class,
        Template::class
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun daoAccess(): IDaoAccess

    companion object {
        private var instance: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "MY_APP_SCHEDULE")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}