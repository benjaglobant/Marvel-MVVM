package com.globant.data.service

import com.globant.domain.entity.Character
import com.globant.data.mapper.CharacterMapper
import com.globant.data.service.api.MarvelApi
import com.globant.data.service.requestgenerator.MarvelRequestGenerator
import com.globant.domain.service.CharactersService
import com.globant.domain.util.Constants.NOT_FOUND
import com.globant.domain.util.Result

class CharacterServiceImpl : CharactersService {

    private val api = MarvelRequestGenerator()
    private val mapper = CharacterMapper()

    override fun getAllCharacters(): Result<List<Character>> {
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

    override fun getSpecificCharacter(characterId: String): Result<List<Character>> {
        try {
            val callResponse = api.createService(MarvelApi::class.java).getSpecificCharacterRequest(characterId)
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