package com.rohitss.aac

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * Created by Rohit Surwase on 07/08/18.
 */

@Dao
interface ArticlesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllArticlesDAO(articlesItem: List<ArticlesItem>)

    @Query("DELETE FROM articles_table")
    fun deleteAllArticlesDAO()

    @Query("SELECT * from articles_table")
    fun getAllArticlesDAO(): LiveData<List<ArticlesItem>>
}