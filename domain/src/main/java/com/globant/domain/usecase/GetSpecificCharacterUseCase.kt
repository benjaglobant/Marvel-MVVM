package com.globant.domain.usecase

import com.globant.domain.util.Result
import com.globant.domain.entity.Character

interface GetSpecificCharacterUseCase {
    fun invoke(characterId: String): Result<List<Character>>
}