package com.rohitss.aac

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao

/**
 * Created by Rohit Surwase on 07/08/18.
 */

@Dao
interface DatabaseDAO {
    fun getAllNewsDAO(): LiveData<List<ArticlesItem>>? {
        return null
    }
}