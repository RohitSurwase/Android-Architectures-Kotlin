package com.rohitss.aac

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context


/**
 * Created by Rohit Surwase on 07/08/18.
 */
@Database(entities = [(ArticlesItem::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDatabaseDAO(): DatabaseDAO

    companion object {
        // For Singleton instantiation
        private val LOCK = Any()
        private var sInstance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if (sInstance == null) {
                synchronized(LOCK) {
                    if (sInstance == null) {
                        sInstance = Room.databaseBuilder(context.applicationContext,
                                AppDatabase::class.java, "my_database.db").build()
                    }
                }
            }
            return sInstance
        }
    }
}