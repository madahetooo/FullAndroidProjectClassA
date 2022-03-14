package com.apps.fullandroidcourseclassa.breakingbadapp.ui.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apps.fullandroidcourseclassa.R
import com.apps.fullandroidcourseclassa.breakingbadapp.model.BBCharacter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.character_item.view.*

class CharacterListAdapter(){

    inner class CharacterViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        private val tvCharacterName:TextView = itemView.findViewById(R.id.tvCharacterName)
        private val tvCharacterNickname:TextView = itemView.findViewById(R.id.tvCharacterNickName)
        private val tvCharacterBirthday:TextView = itemView.findViewById(R.id.tvCharacterBirthday)
        private val tvCharacterOccupation:TextView = itemView.findViewById(R.id.tvCharacterOccupation)
        private val tvCharacterStatus:TextView = itemView.findViewById(R.id.tvCharacterStatus)
        private val ivCharacterImage:ImageView = itemView.findViewById(R.id.ivCharacterImage)

        fun bind(character:BBCharacter){
            tvCharacterName.text = character.name
            tvCharacterNickname.text = character.nickname
            tvCharacterBirthday.text = character.birthday
            tvCharacterOccupation.text = character.occupation.joinToString { "," }
            tvCharacterStatus.text = character.status

            if (character.img !=null){
                Glide.with(itemView).load(character.img).centerCrop().into(ivCharacterImage)
            }
        }

//            fun create(parent:ViewGroup):CharacterViewHolder{
//                val view:View = LayoutInflater.from(parent.context).inflate(parent,R.layout.character_item,false)
//                return CharacterViewHolder(view)
//        }
    }

}