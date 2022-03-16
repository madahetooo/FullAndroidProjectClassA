package com.apps.fullandroidcourseclassa.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apps.fullandroidcourseclassa.databinding.ItemViewPagerBinding

class ViewPagerAdapter(var images: List<Int>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: ItemViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root)
//    val binding = ActivityOurEventsBinding.bind(binding)

    // This property is only valid between onCreateView and
// onDestroyView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager, parent, false)
        return ViewPagerViewHolder(
            ItemViewPagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentEventImage = images[position]
        holder.binding.ivEventImage.setImageResource(currentEventImage)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}