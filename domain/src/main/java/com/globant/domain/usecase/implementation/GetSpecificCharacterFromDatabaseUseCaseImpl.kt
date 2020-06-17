package com.globant.domain.usecase.implementation

import com.globant.domain.entity.Character
import com.globant.domain.util.Result
import com.globant.domain.repository.CharacterRepository
import com.globant.domain.usecase.GetSpecificCharacterFromDatabaseUseCase

class GetSpecificCharacterFromDatabaseUseCaseImpl(private val characterRepository: CharacterRepository) :
    GetSpecificCharacterFromDatabaseUseCase {
    override fun invoke(characterId: String): Result<List<Character>> =
        characterRepository.getSpecificLocalCharacter(characterId)
}