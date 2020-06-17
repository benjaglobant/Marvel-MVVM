package com.globant.marvelmvvm.model

import com.globant.marvelmvvm.contract.SpecificCharacterContract
import com.globant.domain.entity.Character
import com.globant.domain.usecase.GetSpecificCharacterFromDatabaseUseCase
import com.globant.domain.usecase.GetSpecificCharacterUseCase
import com.globant.domain.util.Result

class SpecificCharacterModel(
    private val getSpecificCharacterUseCase: GetSpecificCharacterUseCase,
    private val getSpecificCharacterFromDatabaseUseCase: GetSpecificCharacterFromDatabaseUseCase
) : SpecificCharacterContract.Model {
    override fun getSpecificCharacter(characterId: String): Result<List<Character>> {
        return when (val characters = getSpecificCharacterService(characterId)) {
            is Result.Success -> {
                characters
            }
            is Result.Failure -> {
                getSpecificCharacterDatabase(characterId)
            }
        }
    }

    private fun getSpecificCharacterService(characterId: String): Result<List<Character>> =
        getSpecificCharacterUseCase.invoke(characterId)

    private fun getSpecificCharacterDatabase(characterId: String): Result<List<Character>> =
        getSpecificCharacterFromDatabaseUseCase.invoke(characterId)
}