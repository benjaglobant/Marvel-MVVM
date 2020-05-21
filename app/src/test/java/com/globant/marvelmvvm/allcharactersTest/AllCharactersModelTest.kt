package com.globant.marvelmvvm.allcharactersTest

import com.globant.marvelmvvm.contract.AllCharactersContract
import com.globant.marvelmvvm.data.entity.Character
import com.globant.marvelmvvm.data.repository.MarvelRepository
import com.globant.marvelmvvm.model.AllCharactersModel
import com.globant.marvelmvvm.util.Result
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class AllCharactersModelTest {

    private lateinit var model: AllCharactersContract.Model
    private var mockedRepository: MarvelRepository = mock()
    private var validResult: Result.Success<List<Character>> = mock()
    private var invalidResult: Result.Failure = mock()

    @Before
    fun setUp() {
        model = AllCharactersModel(mockedRepository)
    }

    @Test
    fun `call getAllCharactersFromAPI returns success result`() {
        whenever(mockedRepository.getAllCharactersFromAPI()).thenReturn(validResult)
        assertEquals(validResult, model.getAllCharacters())
        verify(mockedRepository).getAllCharactersFromAPI()
    }

    @Test
    fun `call getAllCharactersFromAPI returns failure result`(){
        whenever(mockedRepository.getAllCharactersFromAPI()).thenReturn(invalidResult)
        assertEquals(invalidResult, model.getAllCharacters())
        verify(mockedRepository).getAllCharactersFromAPI()
    }

}