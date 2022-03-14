package com.apps.fullandroidcourseclassa.breakingbadapp.db

import android.content.Context
import androidx.room.*
import com.apps.fullandroidcourseclassa.breakingbadapp.model.BBCharacter
import com.apps.fullandroidcourseclassa.data.db.ShoppingDatabase

@Database(
    entities = [BBCharacter::class],
    version = 1
)
@TypeConverters(AppTypeConverters::class)
abstract class CharacterDatabase :RoomDatabase(){
    abstract fun createCharacterDao():CharacterDao
    companion object{
        @Volatile
        private var INSTANCE: CharacterDatabase? = null

        fun createDatabase(context: Context):CharacterDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDatabase::class.java,
                    "bbapp.dp"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}