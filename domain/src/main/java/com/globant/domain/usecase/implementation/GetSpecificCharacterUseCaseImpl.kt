package com.globant.domain.usecase.implementation

import com.globant.domain.entity.Character
import com.globant.domain.service.CharactersService
import com.globant.domain.usecase.GetSpecificCharacterUseCase
import com.globant.domain.util.Constants.NOT_FOUND
import com.globant.domain.util.Result

class GetSpecificCharacterUseCaseImpl(private val characterService: CharactersService) : GetSpecificCharacterUseCase {
    override fun invoke(characterId: String): Result<List<Character>> {
        characterService.getSpecificCharacter(characterId).let {
            return when (it) {
                is Result.Success -> it
                is Result.Failure -> Result.Failure(Exception(NOT_FOUND))
            }
        }
    }
}