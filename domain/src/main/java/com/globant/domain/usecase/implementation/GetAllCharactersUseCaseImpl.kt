package com.globant.domain.usecase.implementation

import com.globant.domain.entity.Character
import com.globant.domain.repository.CharacterRepository
import com.globant.domain.usecase.GetAllCharactersUseCase
import com.globant.domain.util.Result

class GetAllCharactersUseCaseImpl(private val characterRepository: CharacterRepository): GetAllCharactersUseCase {
    override fun invoke(): Result<List<Character>> = characterRepository.getAllCharacters()
}