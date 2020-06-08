package com.globant.marvelmvvm.data.service.api

import com.globant.marvelmvvm.data.service.response.MarvelBaseResponse
import com.globant.marvelmvvm.data.service.response.CharacterResponse
import com.globant.marvelmvvm.data.service.response.DataBaseResponse
import com.globant.domain.util.Constants.CHARACTER_ID
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("/v1/public/characters")
    fun getAllCharactersRequest(): Call<MarvelBaseResponse<DataBaseResponse<ArrayList<CharacterResponse>>>>

    @GET("/v1/public/characters")
    fun getSpecificCharacterRequest(@Query(CHARACTER_ID) id: String):
            Call<MarvelBaseResponse<DataBaseResponse<ArrayList<CharacterResponse>>>>
}