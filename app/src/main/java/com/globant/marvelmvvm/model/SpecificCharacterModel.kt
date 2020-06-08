package com.globant.marvelmvvm.model

import com.globant.marvelmvvm.contract.SpecificCharacterContract
import com.globant.domain.entity.Character
import com.globant.domain.usecase.GetSpecificCharacterUseCase
import com.globant.marvelmvvm.data.service.MarvelService
import com.globant.domain.util.Result

class SpecificCharacterModel(private val getSpecificCharacterUseCase: GetSpecificCharacterUseCase) : SpecificCharacterContract.Model {
    override fun getSpecificCharacter(characterId: String): Result<List<Character>> =
        getSpecificCharacterUseCase.invoke(characterId)
}