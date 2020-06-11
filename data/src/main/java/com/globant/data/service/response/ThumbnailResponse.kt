package com.globant.data.service.response

import com.globant.domain.util.Constants.EMPTY_STRING

data class ThumbnailResponse(
    val path: String = EMPTY_STRING,
    val extension: String = EMPTY_STRING
)