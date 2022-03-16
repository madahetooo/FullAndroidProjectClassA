package com.apps.fullandroidcourseclassa.clothesapp.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.fullandroidcourseclassa.databinding.ClothesItemBinding
import com.bumptech.glide.Glide

class ClothesImageAdapter(val urls: List<String>) :
    RecyclerView.Adapter<ClothesImageAdapter.ClothesImageViewHolder>() {
    inner class ClothesImageViewHolder(val binding: ClothesItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesImageViewHolder {
        return ClothesImageViewHolder(
            ClothesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ClothesImageViewHolder, position: Int) {
        val urls = urls[position]
        Glide.with(holder.itemView).load(urls).into(holder.binding.ivClothesItem)
    }

    override fun getItemCount(): Int {
        return urls.size
    }
}