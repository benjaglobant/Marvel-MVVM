package com.globant.data.service.response

import com.globant.domain.util.Constants.EMPTY_STRING

class CharacterResponse(
    val id: String = EMPTY_STRING,
    val name: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val thumbnail: ThumbnailResponse
)