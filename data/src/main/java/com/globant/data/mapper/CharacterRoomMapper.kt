package com.globant.data.mapper

import com.globant.domain.entity.CharacterRoom
import com.globant.domain.entity.Character

class CharacterRoomMapper {

    fun transformToCharacterRepository(character: Character): CharacterRoom =
        CharacterRoom(
            character.id,
            character.name,
            getDescription(character.description),
            character.image
        )

    fun transformToCharacter(characterRoom: CharacterRoom): Character =
        Character(
            characterRoom.id,
            characterRoom.name,
            getDescription(characterRoom.description),
            characterRoom.image
        )

    private fun getDescription(description: String): String =
        if (description.isEmpty()) {
            INVALID_DESCRIPTION
        } else {
            description
        }

    fun transformToListOfCharacters(characters: List<CharacterRoom>): List<Character> =
        characters.map { transformToCharacter(it) }

    companion object {
        const val INVALID_DESCRIPTION = "Not description available"
    }
}