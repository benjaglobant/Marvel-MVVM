package com.globant.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.globant.domain.database.CharacterRoomDatabase
import com.globant.domain.entity.CharacterRoom

@Database(entities = [CharacterRoom::class], version = 1)
abstract class CharacterRoomDatabaseImpl : RoomDatabase(), CharacterRoomDatabase {

    abstract fun characterDao(): CharacterDao

    override fun getAllLocalCharacters(): List<CharacterRoom> = characterDao().getAllCharacters()

    override fun getSpecificLocalCharacter(characterId: String): CharacterRoom = characterDao().getSpecificCharacter(characterId)

    override fun insertLocalCharacters(characters: List<CharacterRoom>) {
        characters.forEach { characterDao().insertCharacter(it) }
    }
}