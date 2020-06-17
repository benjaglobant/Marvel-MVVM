package com.globant.domain.usecase

import com.globant.domain.entity.Character

interface UpdateCharactersDatabaseUseCase {
    fun invoke(characters: List<Character>)
}