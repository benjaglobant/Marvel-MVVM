package com.globant.marvelmvvm.data.service.api

import com.globant.marvelmvvm.data.service.response.MarvelBaseResponse
import com.globant.marvelmvvm.data.service.response.CharacterResponse
import com.globant.marvelmvvm.data.service.response.DataBaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelApi {
    @GET("/v1/public/characters")
    fun getAllCharactersRequest(): Call<MarvelBaseResponse<DataBaseResponse<ArrayList<CharacterResponse>>>>
}