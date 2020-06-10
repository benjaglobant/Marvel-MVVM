package com.globant.data.service.api

import com.globant.data.service.response.MarvelBaseResponse
import com.globant.data.service.response.CharactersBaseResponse
import com.globant.domain.util.Constants.CHARACTER_ID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("/v1/public/characters")
    fun getAllCharactersRequest(): Call<MarvelBaseResponse<CharactersBaseResponse>>

    @GET("/v1/public/characters")
    fun getSpecificCharacterRequest(@Query(CHARACTER_ID) id: String):
            Call<MarvelBaseResponse<CharactersBaseResponse>>
}