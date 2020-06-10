package com.globant.domain.usecase.implementation

import com.globant.domain.entity.Character
import com.globant.domain.service.CharactersService
import com.globant.domain.usecase.GetAllCharactersUseCase
import com.globant.domain.util.Result

class GetAllCharactersUseCaseImpl(private val characterService: CharactersService) : GetAllCharactersUseCase {
    override fun invoke(): Result<List<Character>> = characterService.getAllCharacters()
}