package com.apps.fullandroidcourseclassa.breakingbadapp.repository

import androidx.lifecycle.LiveData
import com.apps.fullandroidcourseclassa.breakingbadapp.db.CharacterDao
import com.apps.fullandroidcourseclassa.breakingbadapp.model.BBCharacter
import com.apps.fullandroidcourseclassa.breakingbadapp.service.BreakingBadNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository(private val characterDao: CharacterDao) {

    var characters:LiveData<List<BBCharacter>> = characterDao.findAllCharacters()
    suspend fun refreshCharacters(){
        withContext(Dispatchers.IO){
            val characters = BreakingBadNetwork.serviceApi.getCharacters()
            characterDao.insertAllCharacters(characters)
        }
    }
}