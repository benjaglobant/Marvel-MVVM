package com.globant.data.service.response

import com.google.gson.annotations.SerializedName

data class DataBaseResponse<T>(
    val results: List<CharacterResponse>,
    val offset: Int,
    val limit: Int,
    val total: Int
)