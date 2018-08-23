package com.rohitss.aac

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * Created by Rohit Surwase on 07/08/18.
 */
@Entity(tableName = "articles_table")
data class ArticlesItem(
        @SerializedName("author") var author: String,
        @SerializedName("description") var description: String,
        @SerializedName("title") var title: String
)