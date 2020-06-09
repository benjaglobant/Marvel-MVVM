package com.globant.data.mapper

import com.globant.data.service.response.CharacterResponse
import com.globant.data.service.response.DataBaseResponse
import com.globant.data.service.response.MarvelBaseResponse
import com.globant.data.service.response.ThumbnailResponse
import com.globant.domain.entity.Character

class CharacterMapper {

    private fun transformToThumbnail(thumbnailResponse: ThumbnailResponse): String =
        "${thumbnailResponse.path}$DOT${thumbnailResponse.extension}"

    private fun transformToCharacter(characterResponse: CharacterResponse): Character =
        characterResponse.let {
            return Character(
                it.id,
                it.name,
                getDescription(it.description),
                transformToThumbnail(it.thumbnail)
            )
        }

    private fun getDescription(description: String): String {
        return if (description.isEmpty())
            INVALID_DESCRIPTION
        else
            description
    }

    fun transformToListOfCharacters(response: MarvelBaseResponse<DataBaseResponse<ArrayList<CharacterResponse>>>): List<Character>? =
        response.data?.results?.map { transformToCharacter(it) }

    companion object {
        const val DOT = "."
        const val INVALID_DESCRIPTION = "Not description available"
    }
}