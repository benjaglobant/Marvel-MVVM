package com.globant.domain.usecase.implementation

import com.globant.domain.entity.Character
import com.globant.domain.util.Result
import com.globant.domain.repository.CharacterRepository
import com.globant.domain.usecase.GetAllCharactersFromDatabaseUseCase

class GetAllCharactersFromDatabaseUseCaseImpl(
    private val characterRepository: CharacterRepository
) : GetAllCharactersFromDatabaseUseCase {
    override fun invoke(): Result<List<Character>> = characterRepository.getAllLocalCharacters()
}
