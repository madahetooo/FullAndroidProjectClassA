package com.apps.fullandroidcourseclassa.breakingbadapp

import android.app.Application
import com.apps.fullandroidcourseclassa.breakingbadapp.db.CharacterDatabase
import com.apps.fullandroidcourseclassa.breakingbadapp.repository.CharacterRepository

class BreakingBadApplication:Application() {
    val database by lazy{
        CharacterDatabase.createDatabase(this@BreakingBadApplication)
    }
    val charachterRepository by lazy {
        CharacterRepository(database.createCharacterDao())
    }
}