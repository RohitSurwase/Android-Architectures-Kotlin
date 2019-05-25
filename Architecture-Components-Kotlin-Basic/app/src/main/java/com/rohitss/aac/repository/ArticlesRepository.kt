/*
 *
 *  * Copyright (C) 2017-19 Rohit Sahebrao Surwase.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.rohitss.aac.repository

import android.text.TextUtils
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.rohitss.aac.BuildConfig
import com.rohitss.aac.model.ArticlesDAO
import com.rohitss.aac.model.ArticlesItem
import com.rohitss.aac.model.NewsResponseNullable
import org.jetbrains.anko.doAsync

/**
 * Created by Rohit Surwase on 07/08/18.
 */
class ArticlesRepository private constructor(private val articlesDAO: ArticlesDAO) {

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: ArticlesRepository? = null

        fun getInstance(databaseDAO: ArticlesDAO): ArticlesRepository = instance
                ?: synchronized(this) {
                    instance
                            ?: ArticlesRepository(databaseDAO).also { instance = it }
                }
    }

    fun getAll() = articlesDAO.getAll()

    fun insertAll(articlesItemList: List<ArticlesItem>) {
        doAsync {
            articlesDAO.insertAll(articlesItemList)
        }
    }

    fun deleteAll() {
        doAsync { articlesDAO.deleteAll() }
    }

    fun requestArticles() {
        AndroidNetworking.get("https://newsapi.org/v2/top-headlines?sources=bbc-news")
                .addHeaders("X-Api-Key", BuildConfig.ApiKey)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(NewsResponseNullable::class.java, object : ParsedRequestListener<NewsResponseNullable> {
                    override fun onResponse(newsResponseNullable: NewsResponseNullable) {
                        val articlesItemList: MutableList<ArticlesItem>? = mutableListOf()
                        /**
                         * Note: we have two different data model- article: ArticlesItem & ArticlesItemNullable
                         * We are getting server data in ArticlesItemNullable and then
                         * filtering it to remove any null or empty item.
                         * This filtered data is then copied to the ArticlesItem item.
                         * This way we are reducing nullability
                         */
                        newsResponseNullable
                                .articles?.asSequence()?.filterNotNull()
                                ?.filterNot { TextUtils.isEmpty(it.author) || TextUtils.isEmpty(it.title) || TextUtils.isEmpty(it.description) }
                                ?.map { ArticlesItem(0, it.author!!, it.description!!, it.title!!) }?.toList()
                                ?.forEach { articlesItemList?.add(it) }

                        if (articlesItemList != null && !articlesItemList.isEmpty()) {
                            insertAll(articlesItemList)
                        } else {
                            Log.d("RohitSS", "List Empty")
                        }
                    }

                    override fun onError(anError: ANError) {
                        Log.d("RohitSS", anError.errorBody)
                    }
                })
    }
}
