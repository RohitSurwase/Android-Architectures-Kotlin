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

package com.rohitss.aac

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_view.view.*

class ArticlesListAdapter(private val arrNewsUpdates: List<ArticlesItem>, private val listener: (Int) -> Unit) : RecyclerView.Adapter<ArticlesListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(inflate(parent.context, R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(arrNewsUpdates[position], listener)
    }

    override fun getItemCount(): Int {
        return arrNewsUpdates.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(articlesItem: ArticlesItem, listener: (Int) -> Unit) = with(itemView) {
            textViewTitle.text = articlesItem.title
            textViewAuthor.text = articlesItem.author
            textViewDescription.text = articlesItem.description
            itemView.setOnClickListener { listener(adapterPosition) }
        }
    }
}