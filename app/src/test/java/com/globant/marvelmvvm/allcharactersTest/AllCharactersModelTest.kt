package com.globant.marvelmvvm.allcharactersTest

import com.globant.marvelmvvm.contract.AllCharactersContract
import com.globant.domain.entity.Character
import com.globant.domain.service.CharactersService
import com.globant.domain.usecase.implementation.GetAllCharactersUseCaseImpl
import com.globant.marvelmvvm.model.AllCharactersModel
import com.globant.domain.util.Result
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class AllCharactersModelTest {

    private lateinit var model: AllCharactersContract.Model
    private var mockedService: CharactersService = mock()
    private var validResult: Result.Success<List<Character>> = mock()
    private var invalidResult: Result.Failure = mock()

    @Before
    fun setUp() {
        model = AllCharactersModel(GetAllCharactersUseCaseImpl(mockedService))
    }

    @Test
    fun `call getAllCharactersFromAPI returns success result`() {
        whenever(mockedService.getAllCharacters()).thenReturn(validResult)
        assertEquals(validResult, model.getAllCharacters())
        verify(mockedService).getAllCharacters()
    }

    @Test
    fun `call getAllCharactersFromAPI returns failure result`() {
        whenever(mockedService.getAllCharacters()).thenReturn(invalidResult)
        assertEquals(invalidResult, model.getAllCharacters())
        verify(mockedService).getAllCharacters()
    }
}