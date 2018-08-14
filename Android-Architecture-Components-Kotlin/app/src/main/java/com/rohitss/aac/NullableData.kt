package com.rohitss.aac

import com.google.gson.annotations.SerializedName

/**
 * Created by Rohit Surwase on 07/08/18.
 */
data class NewsResponseNullable(
        @SerializedName("articles") val articles: List<ArticlesItemNullable?>? = null
)

data class ArticlesItemNullable(
        @SerializedName("author") val author: String? = null,
        @SerializedName("description") val description: String? = null,
        @SerializedName("title") val title: String? = null
)