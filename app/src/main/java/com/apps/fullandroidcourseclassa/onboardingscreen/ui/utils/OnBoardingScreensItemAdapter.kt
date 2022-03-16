package com.apps.fullandroidcourseclassa.onboardingscreen.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.onboardingscreen.model.OnBoardingScreensItem
import com.apps.fullandroidcourseclassa.databinding.OnboardingItemBinding

class OnBoardingScreensItemAdapter (private val onBoardingScreenItem : List<OnBoardingScreensItem>) : RecyclerView.Adapter<OnBoardingScreensItemAdapter.OnBoardingScreenItemViewHolder>(){

    inner class OnBoardingScreenItemViewHolder(val binding:OnboardingItemBinding) : RecyclerView.ViewHolder(binding.root){
        private  val ivOnBoardingImage = itemView.findViewById<ImageView>(R.id.ivOnBoardingImage)
        private  val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private  val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)

        fun bind(onBoardingScreensItem: OnBoardingScreensItem){
            binding.ivOnBoardingImage.setImageResource(onBoardingScreensItem.onBoardingScreensImage)
            binding.tvTitle.text = onBoardingScreensItem.title
            binding.tvDescription.text = onBoardingScreensItem.description

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingScreenItemViewHolder {
        return OnBoardingScreenItemViewHolder(OnboardingItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: OnBoardingScreenItemViewHolder, position: Int) {
        holder.bind(onBoardingScreenItem[position])
    }

    override fun getItemCount(): Int {
        return onBoardingScreenItem.size
    }
}