package com.rohitss.aac

/**
 * Created by Rohit Surwase on 07/08/18.
 */
data class NewsResponseNullable(
        val articles: List<ArticlesItemNullable?>? = null
)

data class ArticlesItemNullable(
        val author: String? = null,
        val description: String? = null,
        val title: String? = null
)