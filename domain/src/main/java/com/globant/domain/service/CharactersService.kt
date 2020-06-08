package com.globant.domain.service

import com.globant.domain.entity.Character
import com.globant.domain.util.Result

interface CharactersService {
    fun getAllCharacters(): Result<List<Character>>
    fun getSpecificCharacter(characterId: String): Result<List<Character>>
}