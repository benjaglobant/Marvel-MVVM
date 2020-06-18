package com.globant.marvelmvvm.allcharactersTest

import com.globant.marvelmvvm.contract.AllCharactersContract
import com.globant.domain.entity.Character
import com.globant.domain.usecase.GetAllCharactersFromDatabaseUseCase
import com.globant.domain.usecase.GetAllCharactersUseCase
import com.globant.domain.usecase.UpdateCharactersDatabaseUseCase
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
    private val mockedGetAllCharactersUseCase: GetAllCharactersUseCase = mock()
    private val mockedGetAllCharactersFromDatabaseUseCase: GetAllCharactersFromDatabaseUseCase = mock()
    private val mockedUpdateCharactersDatabaseUseCase: UpdateCharactersDatabaseUseCase = mock()
    private var validResult: Result.Success<List<Character>> = Result.Success(getMockedList())
    private var invalidResult: Result.Failure = mock()

    @Before
    fun setUp() {
        model = AllCharactersModel(
            mockedGetAllCharactersUseCase,
            mockedGetAllCharactersFromDatabaseUseCase,
            mockedUpdateCharactersDatabaseUseCase
        )
    }

    @Test
    fun `call getAllCharacters remote returns success result, update database`() {
        whenever(mockedGetAllCharactersUseCase.invoke()).thenReturn(validResult)
        assertEquals(validResult, model.getAllCharacters())
        verify(mockedGetAllCharactersUseCase).invoke()
        validResult.data?.let { verify(mockedUpdateCharactersDatabaseUseCase).invoke(it) }
    }

    @Test
    fun `call getAllCharacters remote returns failure result, database returns success result`() {
        whenever(mockedGetAllCharactersUseCase.invoke()).thenReturn(invalidResult)
        whenever(mockedGetAllCharactersFromDatabaseUseCase.invoke()).thenReturn(validResult)
        assertEquals(validResult, model.getAllCharacters())
        verify(mockedGetAllCharactersUseCase).invoke()
        verify(mockedGetAllCharactersFromDatabaseUseCase).invoke()
    }

    @Test
    fun `call getAllCharacters remote returns failure result, database returns failure result`() {
        whenever(mockedGetAllCharactersUseCase.invoke()).thenReturn(invalidResult)
        whenever(mockedGetAllCharactersFromDatabaseUseCase.invoke()).thenReturn(invalidResult)
        assertEquals(invalidResult, model.getAllCharacters())
        verify(mockedGetAllCharactersUseCase).invoke()
        verify(mockedGetAllCharactersFromDatabaseUseCase).invoke()
    }

    private fun getMockedList(): List<Character> = listOf(Character(), Character())
}