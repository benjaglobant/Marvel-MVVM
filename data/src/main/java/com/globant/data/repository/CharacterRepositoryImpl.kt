package com.globant.data.repository

import com.globant.data.database.CharacterDao
import com.globant.data.mapper.CharacterRoomMapper
import com.globant.domain.repository.CharacterRepository
import com.globant.domain.entity.Character
import com.globant.domain.util.Result

class CharacterRepositoryImpl(private val characterDao: CharacterDao) : CharacterRepository {

    private val characterRoomMapper = CharacterRoomMapper()

    override fun getAllLocalCharacters(): Result<List<Character>> = Result.Success(
        characterRoomMapper.transformToListOfCharacters(characterDao.getAllCharacters())
    )

    override fun getSpecificLocalCharacter(characterId: String): Result<List<Character>> = Result.Success(
        characterRoomMapper.transformToListOfCharacters (characterDao.getSpecificCharacter(characterId)))

    override fun insertLocalCharacters(characters: List<Character>) {
        characters.forEach { characterDao.insertCharacter(characterRoomMapper.transformToCharacterRepository(it)) }
    }
}