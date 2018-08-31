package com.rohitss.aac

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by Rohit Surwase on 07/08/18.
 */
@Database(entities = [ArticlesItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getArticlesDAO(): ArticlesDAO

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "my_database.db").fallbackToDestructiveMigration().build().also { instance = it }
            }
        }
    }
}