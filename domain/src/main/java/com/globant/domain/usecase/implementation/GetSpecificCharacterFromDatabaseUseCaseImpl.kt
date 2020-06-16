package com.globant.domain.usecase.implementation

import com.globant.domain.database.CharacterRoomDatabase
import com.globant.domain.entity.CharacterRoom
import com.globant.domain.usecase.GetSpecificCharacterFromDatabaseUseCase

class GetSpecificCharacterFromDatabaseUseCaseImpl(private val characterRoomDatabase: CharacterRoomDatabase) :
    GetSpecificCharacterFromDatabaseUseCase {
    override fun invoke(characterId: String): CharacterRoom =
        characterRoomDatabase.getSpecificLocalCharacter(characterId)
}