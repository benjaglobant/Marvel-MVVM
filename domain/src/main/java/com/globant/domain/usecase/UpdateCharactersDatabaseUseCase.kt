package com.globant.domain.usecase

import com.globant.domain.entity.CharacterRoom

interface UpdateCharactersDatabaseUseCase {
    fun invoke(characters: List<CharacterRoom>)
}