package com.apps.fullandroidcourseclassa.breakingbadapp.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.breakingbadapp.model.BBCharacter
import com.apps.fullandroidcourseclassa.databinding.CharacterItemBinding
import com.bumptech.glide.Glide

class CharacterListAdapter(
    private val clickCallBack: ((BBCharacter) -> Unit)?
) : androidx.recyclerview.widget.ListAdapter<BBCharacter, CharacterListAdapter.CharacterViewHolder>(
    CharacterComparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
        holder.itemView.setOnClickListener { clickCallBack?.invoke(character) }
    }

    class CharacterViewHolder(val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val tvCharacterName: TextView = itemView.findViewById(R.id.tvCharacterName)
        private val tvCharacterNickname: TextView = itemView.findViewById(R.id.tvCharacterNickName)
        private val tvCharacterBirthday: TextView = itemView.findViewById(R.id.tvCharacterBirthday)
        private val tvCharacterOccupation: TextView = itemView.findViewById(R.id.tvCharacterOccupation)
        private val tvCharacterStatus: TextView = itemView.findViewById(R.id.tvCharacterStatus)
        private val ivCharacterImage: ImageView = itemView.findViewById(R.id.ivCharacterImage)

        fun bind(character: BBCharacter) {
            tvCharacterName.text = character.name
            tvCharacterNickname.text = character.nickname
            tvCharacterBirthday.text = character.birthday
            tvCharacterOccupation.text = character.occupation.joinToString (", ")
            tvCharacterStatus.text = character.status

            if (character.img != null) {
                Glide.with(itemView).load(character.img).centerCrop().into(ivCharacterImage)
            }
        }

        companion object {
            fun create(parent: ViewGroup): CharacterViewHolder {
                return CharacterViewHolder(
                    CharacterItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    }

}

class CharacterComparator : DiffUtil.ItemCallback<BBCharacter>() {
    override fun areItemsTheSame(oldItem: BBCharacter, newItem: BBCharacter): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: BBCharacter, newItem: BBCharacter): Boolean {
        return oldItem.name == newItem.name
                && oldItem.nickname == newItem.nickname
                && oldItem.status == newItem.status
                && oldItem.img == newItem.img
                && oldItem.birthday == newItem.birthday
                && oldItem.occupation == newItem.occupation
    }
}