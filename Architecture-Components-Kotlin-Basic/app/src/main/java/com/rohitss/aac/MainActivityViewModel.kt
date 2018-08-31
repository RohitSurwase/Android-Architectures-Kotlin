package com.rohitss.aac

import android.arch.lifecycle.ViewModel

/**
 * Created by Rohit Surwase on 29/08/18.
 */
class MainActivityViewModel internal constructor(private val appsRepository: AppsRepository) : ViewModel() {

    fun getAllArticlesREPO() = appsRepository.getAllArticlesREPO()

    fun insertArticleREPO(articlesItem: ArticlesItem) {
        appsRepository.insertArticleREPO(articlesItem)
    }
}