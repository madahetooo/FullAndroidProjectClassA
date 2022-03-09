package com.apps.fullandroidcourseclassa.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.fullandroidcourseclassa.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.clothes_item.view.*

class ClothesImageAdapter(val urls: List<String>) :
    RecyclerView.Adapter<ClothesImageAdapter.ClothesImageViewHolder>() {
    inner class ClothesImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesImageViewHolder {
        return ClothesImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.clothes_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ClothesImageViewHolder, position: Int) {
        val urls = urls[position]
        Glide.with(holder.itemView).load(urls).into(holder.itemView.ivClothesItem)
    }

    override fun getItemCount(): Int {
        return urls.size
    }
}