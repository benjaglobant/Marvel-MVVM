package com.globant.marvelmvvm.model

import com.globant.marvelmvvm.contract.SpecificCharacterContract
import com.globant.marvelmvvm.data.entity.Character
import com.globant.marvelmvvm.data.service.MarvelService
import com.globant.marvelmvvm.util.Result

class SpecificCharacterModel(private val service: MarvelService) : SpecificCharacterContract.Model {
    override fun getSpecificCharacter(characterId: String): Result<List<Character>> =
        service.getSpecificCharacterFromAPI(characterId)
}