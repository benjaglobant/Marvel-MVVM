package com.globant.domain.usecase.implementation

import com.globant.domain.entity.Character
import com.globant.domain.repository.CharacterRepository
import com.globant.domain.usecase.UpdateCharactersDatabaseUseCase

class UpdateCharactersDatabaseUseCaseImpl(
    private val characterRepository: CharacterRepository
) : UpdateCharactersDatabaseUseCase {
    override fun invoke(characters: List<Character>) {
        characterRepository.insertLocalCharacters(characters)
    }
}
