package com.globant.data.mapper

import com.globant.data.service.response.CharacterResponse
import com.globant.data.service.response.CharactersBaseResponse
import com.globant.data.service.response.MarvelBaseResponse
import com.globant.data.service.response.ThumbnailResponse
import com.globant.domain.entity.Character

class CharacterMapper {

    private fun transformToThumbnail(thumbnailResponse: ThumbnailResponse): String =
        "${thumbnailResponse.path}$DOT${thumbnailResponse.extension}"

    private fun transformToCharacter(characterResponse: CharacterResponse): Character =
        Character(
            characterResponse.id,
            characterResponse.name,
            getDescription(characterResponse.description),
            transformToThumbnail(characterResponse.thumbnail)
        )

    private fun getDescription(description: String): String =
        if (description.isEmpty()) {
            INVALID_DESCRIPTION
        } else {
            description
        }

    fun transformToListOfCharacters(response: MarvelBaseResponse<CharactersBaseResponse>): List<Character>? =
        response.data?.characters?.map { transformToCharacter(it) }

    companion object {
        private const val DOT = "."
        private const val INVALID_DESCRIPTION = "Not description available"
    }
}
