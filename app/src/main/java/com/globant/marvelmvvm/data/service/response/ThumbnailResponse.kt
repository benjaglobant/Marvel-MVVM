package com.globant.marvelmvvm.data.service.response

import com.globant.marvelmvvm.util.Constants.EMPTY_STRING

data class ThumbnailResponse(
    val path: String = EMPTY_STRING,
    val extension: String = EMPTY_STRING
)