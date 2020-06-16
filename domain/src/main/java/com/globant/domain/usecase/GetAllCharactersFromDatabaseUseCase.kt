package com.globant.domain.usecase

import com.globant.domain.entity.CharacterRoom

interface GetAllCharactersFromDatabaseUseCase {
    fun invoke(): List<CharacterRoom>
}