package com.rohitss.aac

import org.jetbrains.anko.doAsync

/**
 * Created by Rohit Surwase on 07/08/18.
 */
class AppsRepository private constructor(private val databaseDAO: ArticlesDAO) {

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: AppsRepository? = null

        fun getInstance(databaseDAO: ArticlesDAO) = instance
                ?: synchronized(this) {
            instance
                    ?: AppsRepository(databaseDAO).also { instance = it }
        }
    }

    fun getAllArticlesREPO() = databaseDAO.getAllArticlesDAO()

    fun insertArticleREPO(articlesItem: ArticlesItem) {
        doAsync {

        }
    }
}
