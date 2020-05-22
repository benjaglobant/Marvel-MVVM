package com.globant.marvelmvvm.model

import com.globant.marvelmvvm.contract.AllCharactersContract
import com.globant.marvelmvvm.data.entity.Character
import com.globant.marvelmvvm.data.service.MarvelService
import com.globant.marvelmvvm.util.Result

class AllCharactersModel(private val service: MarvelService) : AllCharactersContract.Model {
    override fun getAllCharacters(): Result<List<Character>> = service.getAllCharactersFromAPI()
}