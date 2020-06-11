package com.globant.domain.entity

import com.globant.domain.util.Constants.EMPTY_STRING

data class Character(
    val id: String = EMPTY_STRING,
    val name: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val image: String = EMPTY_STRING
)