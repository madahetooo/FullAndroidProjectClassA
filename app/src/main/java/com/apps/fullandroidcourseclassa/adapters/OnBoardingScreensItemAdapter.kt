package com.apps.fullandroidcourseclassa.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.data.db.model.OnBoardingScreensItem

class OnBoardingScreensItemAdapter (private val onBoardingScreenItem : List<OnBoardingScreensItem>) : RecyclerView.Adapter<OnBoardingScreensItemAdapter.OnBoardingScreenItemViewHolder>(){

    inner class OnBoardingScreenItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private  val ivOnBoardingImage = itemView.findViewById<ImageView>(R.id.ivOnBoardingImage)
        private  val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private  val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)

        fun bind(onBoardingScreensItem: OnBoardingScreensItem){
            ivOnBoardingImage.setImageResource(onBoardingScreensItem.onBoardingScreensImage)
            tvTitle.text = onBoardingScreensItem.title
            tvDescription.text = onBoardingScreensItem.description

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingScreenItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.onboarding_item, parent, false)
        return OnBoardingScreenItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnBoardingScreenItemViewHolder, position: Int) {
        holder.bind(onBoardingScreenItem[position])
    }

    override fun getItemCount(): Int {
        return onBoardingScreenItem.size
    }
}