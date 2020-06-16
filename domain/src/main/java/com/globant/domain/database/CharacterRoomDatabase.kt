package com.globant.domain.database

import com.globant.domain.entity.CharacterRoom

interface CharacterRoomDatabase {
    fun getAllLocalCharacters(): List<CharacterRoom>
    fun getSpecificLocalCharacter(characterId: String): CharacterRoom
    fun insertLocalCharacters(characters: List<CharacterRoom>)
}