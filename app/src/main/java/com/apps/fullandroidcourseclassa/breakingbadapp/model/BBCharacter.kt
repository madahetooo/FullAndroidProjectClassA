package com.apps.fullandroidcourseclassa.breakingbadapp.model

import androidx.room.Entity

@Entity(tableName = "character")
data class BBCharacter(
    val id:Int,
    val name:String,
    val birthday:String,
    val occupation:Array<String>,
    val img:String?,
    val status:String,
    val nickname:String
) {
}