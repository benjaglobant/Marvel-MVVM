package com.globant.domain.repository

import com.globant.domain.entity.Character
import com.globant.domain.util.Result

interface CharacterRepository {
    fun getAllLocalCharacters(): Result<List<Character>>
    fun getSpecificLocalCharacter(characterId: String): Result<List<Character>>
    fun insertLocalCharacters(characters: List<Character>)
}