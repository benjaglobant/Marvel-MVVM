package com.globant.di

import com.globant.data.service.CharacterServiceImpl
import com.globant.domain.service.CharactersService
import com.globant.domain.usecase.GetAllCharactersUseCase
import com.globant.domain.usecase.GetSpecificCharacterUseCase
import com.globant.domain.usecase.implementation.GetAllCharactersUseCaseImpl
import com.globant.domain.usecase.implementation.GetSpecificCharacterUseCaseImpl
import org.koin.dsl.module

val servicesModule = module {
    single<CharactersService> { CharacterServiceImpl() }
}

val useCasesModule = module {
    single<GetAllCharactersUseCase> { GetAllCharactersUseCaseImpl(get()) }
    single<GetSpecificCharacterUseCase> { GetSpecificCharacterUseCaseImpl(get()) }
}