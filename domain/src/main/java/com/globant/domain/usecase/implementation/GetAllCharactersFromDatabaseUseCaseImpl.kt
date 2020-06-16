package com.globant.domain.usecase.implementation

import com.globant.domain.database.CharacterRoomDatabase
import com.globant.domain.entity.CharacterRoom
import com.globant.domain.usecase.GetAllCharactersFromDatabaseUseCase

class GetAllCharactersFromDatabaseUseCaseImpl(private val characterRoomDatabase: CharacterRoomDatabase) :
    GetAllCharactersFromDatabaseUseCase {
    override fun invoke(): List<CharacterRoom> = characterRoomDatabase.getAllLocalCharacters()
}