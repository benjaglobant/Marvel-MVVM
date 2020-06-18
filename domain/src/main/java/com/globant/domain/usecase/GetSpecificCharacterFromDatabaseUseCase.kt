package com.globant.domain.usecase

import com.globant.domain.entity.Character
import com.globant.domain.util.Result

interface GetSpecificCharacterFromDatabaseUseCase {
    fun invoke(characterId: String): Result<List<Character>>
}
