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

package com.rohitss.news.home.presenter

import com.rohitss.news.home.model.ArticlesItem
import com.rohitss.news.home.model.NewsHomeInteractor
import com.rohitss.news.home.view.NewsHomeView

/**
 * Created by RohitSS on 27-12-2017.
 */
class NewsHomePresenter(private var newsHomeView: NewsHomeView?, private val newsHomeInteractor: NewsHomeInteractor)
    : NewsHomeInteractor.OnFinishedListener {

    fun getNewsData() {
        newsHomeView?.showProgress()
        newsHomeInteractor.requestNewsDataAPI(this)
    }

    fun onDestroy() {
        newsHomeView = null
    }

    override fun onResultSuccess(arrNewsUpdates: List<ArticlesItem>) {
        newsHomeView?.hideProgress()
        newsHomeView?.setNewsData(arrNewsUpdates)
    }

    override fun onResultFail(strError: String) {
        newsHomeView?.hideProgress()
        newsHomeView?.getDataFailed(strError)
    }

    fun onItemClick(adapterPosition: Int) {
        newsHomeView?.onItemClick(adapterPosition)
    }
}