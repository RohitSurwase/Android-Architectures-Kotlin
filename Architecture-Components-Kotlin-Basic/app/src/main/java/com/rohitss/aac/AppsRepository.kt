package com.rohitss.aac

import android.arch.lifecycle.LiveData
import android.content.Context

/**
 * Created by Rohit Surwase on 07/08/18.
 */
class AppsRepository(context: Context) {
    private var databaseDAO: DatabaseDAO?
    private var mAllArticles: LiveData<List<ArticlesItem>>?

    init {
        val appDatabase = AppDatabase.getInstance(context)
        databaseDAO = appDatabase?.getDatabaseDAO()
        mAllArticles = databaseDAO?.getAllNewsDAO()
    }

    companion object {
        private val LOCK = Any()
        private var sInstance: AppsRepository? = null

        fun getInstance(context: Context): AppsRepository? {
            if (sInstance == null) {
                synchronized(LOCK) {
                    if (sInstance == null) {
                        sInstance = AppsRepository(context)
                    }
                }
            }
            return sInstance
        }
    }


    fun getAllArticlesRepo(): LiveData<List<ArticlesItem>>? {
        return mAllArticles
    }
}