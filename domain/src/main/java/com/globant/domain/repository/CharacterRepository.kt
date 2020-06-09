package com.globant.domain.repository

import com.globant.domain.entity.Character
import com.globant.domain.util.Result

interface CharacterRepository {
    fun getSpecificCharacter(characterId: String): Result<List<Character>>
    fun getAllCharacters(): Result<List<Character>>
}