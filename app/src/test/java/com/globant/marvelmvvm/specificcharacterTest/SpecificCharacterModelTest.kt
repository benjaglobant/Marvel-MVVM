package com.globant.marvelmvvm.specificcharacterTest

import com.globant.marvelmvvm.contract.SpecificCharacterContract
import com.globant.domain.entity.Character
import com.globant.domain.usecase.GetSpecificCharacterFromDatabaseUseCase
import com.globant.domain.usecase.GetSpecificCharacterUseCase
import com.globant.marvelmvvm.model.SpecificCharacterModel
import com.globant.domain.util.Result
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SpecificCharacterModelTest {

    private lateinit var model: SpecificCharacterContract.Model
    private lateinit var characterId: String
    private var mockedGetSpecificCharacterUseCase: GetSpecificCharacterUseCase = mock()
    private var mockedGetSpecificCharacterFromDatabaseUseCase: GetSpecificCharacterFromDatabaseUseCase = mock()
    private var validResult: Result.Success<List<Character>> = mock()
    private var invalidResult: Result.Failure = mock()

    @Before
    fun setUp() {
        characterId = TEST_CHARACTER_ID
        model = SpecificCharacterModel(mockedGetSpecificCharacterUseCase, mockedGetSpecificCharacterFromDatabaseUseCase)
    }

    @Test
    fun `call getSpecificCharacter from remote returns success result`() {
        whenever(mockedGetSpecificCharacterUseCase.invoke(characterId)).thenReturn(validResult)

        assertEquals(validResult, model.getSpecificCharacter(characterId))

        verify(mockedGetSpecificCharacterUseCase).invoke(characterId)
    }

    @Test
    fun `call getSpecificCharacter from remote returns failure result, database returns success result`() {
        whenever(mockedGetSpecificCharacterUseCase.invoke(characterId)).thenReturn(invalidResult)
        whenever(mockedGetSpecificCharacterFromDatabaseUseCase.invoke(characterId)).thenReturn(validResult)

        assertEquals(validResult, model.getSpecificCharacter(characterId))

        verify(mockedGetSpecificCharacterUseCase).invoke(characterId)
        verify(mockedGetSpecificCharacterFromDatabaseUseCase).invoke(characterId)
    }

    @Test
    fun `call getSpecificCharacter from remote returns failure result, database returns failure result`() {
        whenever(mockedGetSpecificCharacterUseCase.invoke(characterId)).thenReturn(invalidResult)
        whenever(mockedGetSpecificCharacterFromDatabaseUseCase.invoke(characterId)).thenReturn(invalidResult)

        assertEquals(invalidResult, model.getSpecificCharacter(characterId))

        verify(mockedGetSpecificCharacterUseCase).invoke(characterId)
        verify(mockedGetSpecificCharacterFromDatabaseUseCase).invoke(characterId)
    }

    companion object {
        const val TEST_CHARACTER_ID = "1011334"
    }
}
