package com.globant.marvelmvvm.di

import com.globant.marvelmvvm.contract.AllCharactersContract
import com.globant.marvelmvvm.contract.SpecificCharacterContract
import com.globant.marvelmvvm.model.AllCharactersModel
import com.globant.marvelmvvm.model.SpecificCharacterModel
import com.globant.marvelmvvm.viewmodel.AllCharactersViewModel
import com.globant.marvelmvvm.viewmodel.SpecificCharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { AllCharactersViewModel(get()) }
    viewModel { SpecificCharacterViewModel(get()) }
}

val modelsModule = module {
    single<AllCharactersContract.Model> { AllCharactersModel(get(), get(), get()) }
    single<SpecificCharacterContract.Model> { SpecificCharacterModel(get(), get()) }
}
