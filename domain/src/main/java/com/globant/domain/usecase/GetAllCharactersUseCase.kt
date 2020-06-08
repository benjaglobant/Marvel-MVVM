package com.globant.domain.usecase

import com.globant.domain.util.Result
import com.globant.domain.entity.Character

interface GetAllCharactersUseCase {
    fun invoke(): Result<List<Character>>
}