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

package com.rohitss.aac.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.rohitss.aac.R
import com.rohitss.aac.data.AppDatabase
import com.rohitss.aac.repository.ArticlesRepository
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    private lateinit var articlesViewModel: ArticlesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory: ArticlesViewModel.Factory = ArticlesViewModel.Factory(ArticlesRepository.getInstance(AppDatabase.getInstance(this).getArticlesDAO()))
        articlesViewModel = ViewModelProviders.of(this, factory).get(ArticlesViewModel::class.java)

        mainRecyclerView.layoutManager = LinearLayoutManager(this)

        articlesViewModel.getAll().observe(this, Observer { articles ->
            if (articles != null) {
                val adapter = ArticlesListAdapter(articles) { adapterPosition ->
                    toast(articles[adapterPosition].title)
                }
                mainRecyclerView.adapter = adapter
            }
        })

        floatingActionButtonRefresh.setOnClickListener {
            articlesViewModel.requestArticles()
        }

        floatingActionButtonDelete.setOnClickListener { articlesViewModel.deleteAll() }
    }
}
