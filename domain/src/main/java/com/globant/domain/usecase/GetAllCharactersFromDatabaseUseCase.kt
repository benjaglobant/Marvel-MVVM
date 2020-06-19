package com.globant.domain.usecase

import com.globant.domain.entity.Character
import com.globant.domain.util.Result

interface GetAllCharactersFromDatabaseUseCase {
    fun invoke(): Result<List<Character>>
}
