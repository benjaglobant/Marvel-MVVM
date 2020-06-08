package com.globant.marvelmvvm.model

import com.globant.marvelmvvm.contract.AllCharactersContract
import com.globant.domain.entity.Character
import com.globant.domain.usecase.GetAllCharactersUseCase
import com.globant.marvelmvvm.data.service.MarvelService
import com.globant.domain.util.Result

class AllCharactersModel(private val getAllCharactersUseCase: GetAllCharactersUseCase) : AllCharactersContract.Model {
    override fun getAllCharacters(): Result<List<Character>> = getAllCharactersUseCase.invoke()
}