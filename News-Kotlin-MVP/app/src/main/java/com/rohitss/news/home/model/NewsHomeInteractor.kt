/*
 * Copyright (C) 2017 Rohit Sahebrao Surwase.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rohitss.news.home.model

import android.text.TextUtils
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.rohitss.news.BuildConfig

/**
 * Created by RohitSS on 27-12-2017.
 */
class NewsHomeInteractor {
    companion object {
        private val TAG: String = NewsHomeInteractor::class.java.simpleName
    }

    interface OnFinishedListener {
        fun onResultSuccess(arrNewsUpdates: List<ArticlesItem>)
        fun onResultFail(strError: String)
    }

    fun requestNewsDataAPI(onFinishedListener: OnFinishedListener) {
        //NewsKotlinMvpApiKey is personal ApiKey required for web-service used in this project
        // and I got it after registering at https://newsapi.org and is stored locally.
        //You need to get your own ApiKey by registering at https://newsapi.org/register
        //It is a free service for individual and I do not relate with it in any way.
        AndroidNetworking.get("https://newsapi.org/v2/top-headlines?sources=bbc-news")
                .addHeaders("X-Api-Key", BuildConfig.NewsKotlinMvpApiKey)
                .setTag(TAG)
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
                                ?.map { ArticlesItem(it.author!!, it.description!!, it.title!!) }?.toList()
                                ?.forEach { articlesItemList?.add(it) }

                        if (articlesItemList != null && !articlesItemList.isEmpty()) {
                            onFinishedListener.onResultSuccess(articlesItemList)
                        } else {
                            onFinishedListener.onResultFail("Nothing to show")
                        }
                    }

                    override fun onError(anError: ANError) {
                        // handle error
                        onFinishedListener.onResultFail("Something went wrong")
                    }
                })
    }
}