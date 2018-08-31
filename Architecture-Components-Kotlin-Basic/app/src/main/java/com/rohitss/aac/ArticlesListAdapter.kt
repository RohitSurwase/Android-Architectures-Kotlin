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