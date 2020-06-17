package com.globant.marvelmvvm.model

import com.globant.marvelmvvm.contract.AllCharactersContract
import com.globant.domain.entity.Character
import com.globant.domain.usecase.GetAllCharactersFromDatabaseUseCase
import com.globant.domain.usecase.GetAllCharactersUseCase
import com.globant.domain.usecase.UpdateCharactersDatabaseUseCase
import com.globant.domain.util.Result

class AllCharactersModel(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getAllCharactersFromDatabaseUseCase: GetAllCharactersFromDatabaseUseCase,
    private val updateCharactersDatabaseUseCase: UpdateCharactersDatabaseUseCase
) : AllCharactersContract.Model {

    override fun getAllCharacters(): Result<List<Character>> {
        return when (val characters = getAllCharactersService()) {
            is Result.Success -> {
                characters.data?.let { updateCharactersDatabaseUseCase.invoke(it) }
                characters
            }
            is Result.Failure -> {
                getAllCharactersDatabase()
            }
        }
    }

    private fun getAllCharactersService(): Result<List<Character>> = getAllCharactersUseCase.invoke()

    private fun getAllCharactersDatabase(): Result<List<Character>> = getAllCharactersFromDatabaseUseCase.invoke()
}