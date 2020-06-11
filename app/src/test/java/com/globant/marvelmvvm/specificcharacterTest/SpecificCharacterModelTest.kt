package com.globant.marvelmvvm.specificcharacterTest

import com.globant.marvelmvvm.contract.SpecificCharacterContract
import com.globant.domain.entity.Character
import com.globant.domain.service.CharactersService
import com.globant.domain.usecase.implementation.GetSpecificCharacterUseCaseImpl
import com.globant.marvelmvvm.model.SpecificCharacterModel
import com.globant.domain.util.Result
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class SpecificCharacterModelTest {

    private lateinit var model: SpecificCharacterContract.Model
    private lateinit var characterId: String
    private var mockedService: CharactersService = mock()
    private var validResult: Result.Success<List<Character>> = mock()
    private var invalidResult: Result.Failure = mock()

    @Before
    fun setUp() {
        characterId = TEST_CHARACTER_ID
        model = SpecificCharacterModel(GetSpecificCharacterUseCaseImpl(mockedService))
    }

    @Test
    fun `call getSpecificCharacterFromAPI returns success result`() {
        whenever(mockedService.getSpecificCharacter(characterId)).thenReturn(validResult)
        assertEquals(validResult, model.getSpecificCharacter(characterId))
        verify(mockedService).getSpecificCharacter(characterId)
    }

    @Test
    fun `call getSpecificCharacterFromAPI returns failure result`() {
        whenever(mockedService.getSpecificCharacter(characterId)).thenReturn(invalidResult)
        assertEquals(invalidResult, model.getSpecificCharacter(characterId))
        verify(mockedService).getSpecificCharacter(characterId)
    }

    companion object {
        const val TEST_CHARACTER_ID = "1011334"
    }
}