package com.rohitss.aac

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

/**
 * Created by Rohit Surwase on 07/08/18.
 */
@Entity(tableName = "articles_table")
data class ArticlesItem(
        @PrimaryKey(autoGenerate = true) @NonNull var id: Int,
        var author: String,
        var description: String,
        var title: String
)