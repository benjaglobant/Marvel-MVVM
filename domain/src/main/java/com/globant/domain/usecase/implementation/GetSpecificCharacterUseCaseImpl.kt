package com.globant.domain.usecase.implementation

import com.globant.domain.entity.Character
import com.globant.domain.repository.CharacterRepository
import com.globant.domain.usecase.GetSpecificCharacterUseCase
import com.globant.domain.util.Result

class GetSpecificCharacterUseCaseImpl(private val characterRepository: CharacterRepository): GetSpecificCharacterUseCase {
    override fun invoke(characterId: String): Result<List<Character>> = characterRepository.getSpecificCharacter(characterId)
}