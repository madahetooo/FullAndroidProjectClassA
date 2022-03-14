package com.apps.fullandroidcourseclassa.breakingbadapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apps.fullandroidcourseclassa.breakingbadapp.model.BBCharacter

@Dao
interface CharacterDao {

    @Query("select * from character")
    fun findAllCharacters() : LiveData<List<BBCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(charactersList : List<BBCharacter>)



}