package com.selimabbas.album.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selimabbas.album.R
import com.selimabbas.repository.model.Photo
import kotlinx.android.synthetic.main.item_album.view.*

class PhotoViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_album, parent, false
        )
    ) {

    fun bind(item: Photo) {
        item.title?.let {
            itemView.title.text = it
        }
        item.url?.let {
            Glide.with(itemView)
                .load(it)
                .into(itemView.photo)
        }
    }
}