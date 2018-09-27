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

package com.rohitss.news.home.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.rohitss.news.R
import com.rohitss.news.home.model.ArticlesItem
import com.rohitss.news.home.model.NewsHomeInteractor
import com.rohitss.news.home.presenter.NewsHomePresenter
import com.rohitss.news.showToast
import kotlinx.android.synthetic.main.activity_news_home.*

/**
 * Created by RohitSS on 27-12-2017.
 */
class NewsHomeActivity : AppCompatActivity(), NewsHomeView {
    private lateinit var newsHomePresenter: NewsHomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_home)
        newsHomePresenter = NewsHomePresenter(this, NewsHomeInteractor())
        progressBar.visibility = View.GONE
        recyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        newsHomePresenter.getNewsData()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setNewsData(arrNewsUpdates: List<ArticlesItem>) {
//        recyclerView.adapter = NewsListAdapter(arrNewsUpdates, newsHomePresenter::onItemClick)    // OR
        recyclerView.adapter = NewsListAdapter(arrNewsUpdates) {
            newsHomePresenter.onItemClick(it)
        }
    }

    override fun getDataFailed(strError: String) {
        showToast(this, strError)
    }

    override fun onItemClick(adapterPosition: Int) {
        showToast(this, "You clicked $adapterPosition")
    }

    override fun onDestroy() {
        newsHomePresenter.onDestroy()
        super.onDestroy()
    }
}
