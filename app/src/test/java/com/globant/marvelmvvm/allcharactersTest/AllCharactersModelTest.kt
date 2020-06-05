package com.globant.marvelmvvm.allcharactersTest

import com.globant.marvelmvvm.contract.AllCharactersContract
import com.globant.marvelmvvm.data.entity.Character
import com.globant.marvelmvvm.data.service.MarvelService
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
    private var mockedService: MarvelService = mock()
    private var validResult: Result.Success<List<Character>> = mock()
    private var invalidResult: Result.Failure = mock()

    @Before
    fun setUp() {
        model = AllCharactersModel(mockedService)
    }

    @Test
    fun `call getAllCharactersFromAPI returns success result`() {
        whenever(mockedService.getAllCharactersFromAPI()).thenReturn(validResult)
        assertEquals(validResult, model.getAllCharacters())
        verify(mockedService).getAllCharactersFromAPI()
    }

    @Test
    fun `call getAllCharactersFromAPI returns failure result`() {
        whenever(mockedService.getAllCharactersFromAPI()).thenReturn(invalidResult)
        assertEquals(invalidResult, model.getAllCharacters())
        verify(mockedService).getAllCharactersFromAPI()
    }
}