package com.globant.marvelmvvm.data.service

import com.globant.marvelmvvm.data.entity.Character
import com.globant.marvelmvvm.data.mapper.MarvelMapper
import com.globant.marvelmvvm.data.service.api.MarvelApi
import com.globant.marvelmvvm.data.service.requestgenerator.MarvelRequestGenerator
import com.globant.marvelmvvm.util.Constants.NOT_FOUND
import com.globant.marvelmvvm.util.Result

class MarvelService {

    private val api = MarvelRequestGenerator()
    private val mapper = MarvelMapper()

    fun getAllCharactersFromAPI(): Result<List<Character>> {
        try {
            val callResponse = api.createService(MarvelApi::class.java).getAllCharactersRequest()
            val response = callResponse.execute()
            if (response.isSuccessful)
                response.body()?.let {
                    mapper.transformToListOfCharacters(it)
                }.let {
                    return Result.Success(it)
                }
        } catch (e: Exception) {
            return Result.Failure(Exception(NOT_FOUND))
        }
        return Result.Failure(Exception(NOT_FOUND))
    }
}