package com.globant.marvelmvvm.data.service.response

import com.globant.marvelmvvm.util.Constants.Companion.EMPTY_STRING

class CharacterResponse(
    val id: String = EMPTY_STRING,
    val thumbnail: ThumbnailResponse
)