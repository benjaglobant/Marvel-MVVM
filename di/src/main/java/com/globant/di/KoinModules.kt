package com.globant.di

import androidx.room.Room
import com.globant.data.database.CharacterDatabase
import com.globant.data.repository.CharacterRepositoryImpl
import com.globant.data.service.CharacterServiceImpl
import com.globant.domain.repository.CharacterRepository
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
    single { Room.databaseBuilder(get(), CharacterDatabase::class.java, DATA_BASE_NAME).build() }
    single { get<CharacterDatabase>().characterDao() }
}

val repositoryModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }
}

private const val DATA_BASE_NAME = "characters_database"