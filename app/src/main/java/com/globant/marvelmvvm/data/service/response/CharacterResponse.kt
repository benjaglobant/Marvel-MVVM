package com.globant.marvelmvvm.data.service.response

import com.globant.marvelmvvm.util.Constants.EMPTY_STRING

class CharacterResponse(
    val id: String = EMPTY_STRING,
    val name: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val thumbnail: ThumbnailResponse
)