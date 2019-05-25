/*
 *
 *  * Copyright (C) 2017 Rohit Sahebrao Surwase.
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

package com.rohitss.aac.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.rohitss.aac.model.ArticlesItem
import com.rohitss.aac.repository.ArticlesRepository

/**
 * Created by Rohit Surwase on 29/08/18.
 */
class ArticlesViewModel internal constructor(private val articlesRepository: ArticlesRepository) : ViewModel() {

    init {
        requestArticles()
    }

    fun requestArticles() = articlesRepository.requestArticles()


    fun getAll() = articlesRepository.getAll()

    fun insertAll(articlesItemList: List<ArticlesItem>) {
        articlesRepository.insertAll(articlesItemList)
    }

    fun deleteAll() {
        articlesRepository.deleteAll()
    }

    class Factory(private val articlesRepository: ArticlesRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>) = ArticlesViewModel(articlesRepository) as T
    }
}