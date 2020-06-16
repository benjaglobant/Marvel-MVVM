package com.globant.domain.usecase

import com.globant.domain.entity.CharacterRoom

interface GetSpecificCharacterFromDatabaseUseCase {
    fun invoke(characterId: String): CharacterRoom
}