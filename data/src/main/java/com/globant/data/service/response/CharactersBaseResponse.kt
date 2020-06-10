package com.globant.data.service.response

import com.google.gson.annotations.SerializedName

data class CharactersBaseResponse(
    @SerializedName("results") val characters: List<CharacterResponse>,
    val offset: Int,
    val limit: Int,
    val total: Int
)