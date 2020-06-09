package com.globant.data.service.response

data class MarvelBaseResponse<T>(
    val code: Int,
    val status: String,
    val data: T?
)