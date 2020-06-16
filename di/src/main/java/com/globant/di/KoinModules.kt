package com.globant.di

import androidx.room.Room
import com.globant.data.database.CharacterRoomDatabaseImpl
import com.globant.data.service.CharacterServiceImpl
import com.globant.domain.service.CharactersService
import com.globant.domain.usecase.GetAllCharactersFromDatabaseUseCase
import com.globant.domain.usecase.GetAllCharactersUseCase
import com.globant.domain.usecase.GetSpecificCharacterFromDatabaseUseCase
import com.globant.domain.usecase.GetSpecificCharacterUseCase
import com.globant.domain.usecase.UpdateCharactersDatabaseUseCase
import com.globant.domain.usecase.implementation.GetAllCharactersFromDatabaseUseCaseImpl
import com.globant.domain.usecase.implementation.GetAllCharactersUseCaseImpl
import com.globant.domain.usecase.implementation.GetSpecificCharacterFromDatabaseUseCaseImpl
import com.globant.domain.usecase.implementation.GetSpecificCharacterUseCaseImpl
import com.globant.domain.usecase.implementation.UpdateCharactersDatabaseUseCaseImpl
import org.koin.dsl.module

val servicesModule = module {
    single<CharactersService> { CharacterServiceImpl() }
}

val useCasesModule = module {
    single<GetAllCharactersUseCase> { GetAllCharactersUseCaseImpl(get()) }
    single<GetAllCharactersFromDatabaseUseCase> { GetAllCharactersFromDatabaseUseCaseImpl(get()) }
    single<GetSpecificCharacterUseCase> { GetSpecificCharacterUseCaseImpl(get()) }
    single<GetSpecificCharacterFromDatabaseUseCase> { GetSpecificCharacterFromDatabaseUseCaseImpl(get()) }
    single<UpdateCharactersDatabaseUseCase> { UpdateCharactersDatabaseUseCaseImpl(get()) }
}

val dataBaseModule = module {
    single { Room.databaseBuilder(get(), CharacterRoomDatabaseImpl::class.java, DATA_BASE_NAME).build() }
    single { get<CharacterRoomDatabaseImpl>().characterDao() }
}

private const val DATA_BASE_NAME = "characters_database"