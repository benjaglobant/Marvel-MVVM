package com.globant.domain.usecase.implementation

import com.globant.domain.database.CharacterRoomDatabase
import com.globant.domain.entity.CharacterRoom
import com.globant.domain.usecase.UpdateCharactersDatabaseUseCase

class UpdateCharactersDatabaseUseCaseImpl(private val characterRoomDatabase: CharacterRoomDatabase) :
    UpdateCharactersDatabaseUseCase {
    override fun invoke(characters: List<CharacterRoom>) {
        characterRoomDatabase.insertLocalCharacters(characters)
    }
}