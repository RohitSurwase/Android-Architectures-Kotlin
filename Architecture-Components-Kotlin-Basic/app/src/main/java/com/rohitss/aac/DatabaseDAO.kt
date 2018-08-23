package com.rohitss.aac

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.uiThread

/**
 * Created by Rohit Surwase on 07/08/18.
 */

@Dao
interface DatabaseDAO {
    fun getAllNewsDAO(): LiveData<List<ArticlesItem>>? {
        doAsync {

            uiThread {  }
        }

        doAsyncResult {

            uiThread {  }
        }

        return null
    }
}