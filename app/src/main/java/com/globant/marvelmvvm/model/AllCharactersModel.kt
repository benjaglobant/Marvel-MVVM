package com.globant.marvelmvvm.model

import com.globant.marvelmvvm.contract.AllCharactersContract
import com.globant.marvelmvvm.data.entity.Character
import com.globant.marvelmvvm.data.repository.MarvelRepository
import com.globant.marvelmvvm.util.Result

class AllCharactersModel(private val repository: MarvelRepository) : AllCharactersContract.Model {
    override fun getAllCharacters(): Result<List<Character>> = repository.getAllCharactersFromAPI()
}