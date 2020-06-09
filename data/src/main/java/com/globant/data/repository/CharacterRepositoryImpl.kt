package com.globant.data.repository

import com.globant.domain.repository.CharacterRepository
import com.globant.domain.service.CharactersService
import com.globant.domain.util.Constants.NOT_FOUND
import com.globant.domain.util.Result
import com.globant.domain.entity.Character

class CharacterRepositoryImpl(private val characterService: CharactersService) : CharacterRepository {

    override fun getAllCharacters(): Result<List<Character>> {
        characterService.getAllCharacters().let {
            return when (it) {
                is Result.Success -> it
                is Result.Failure -> Result.Failure(Exception(NOT_FOUND))
                else -> Result.Failure(Exception(NOT_FOUND))
            }
        }
    }

    override fun getSpecificCharacter(characterId: String): Result<List<Character>> {
        characterService.getAllCharacters().let {
            return when (it) {
                is Result.Success -> it
                is Result.Failure -> Result.Failure(Exception(NOT_FOUND))
                else -> Result.Failure(Exception(NOT_FOUND))
            }
        }
    }
}