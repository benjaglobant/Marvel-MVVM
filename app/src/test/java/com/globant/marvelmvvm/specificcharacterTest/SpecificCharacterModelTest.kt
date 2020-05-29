package com.globant.marvelmvvm.specificcharacterTest

import com.globant.marvelmvvm.contract.SpecificCharacterContract
import com.globant.marvelmvvm.data.entity.Character
import com.globant.marvelmvvm.data.service.MarvelService
import com.globant.marvelmvvm.model.SpecificCharacterModel
import com.globant.marvelmvvm.util.Result
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SpecificCharacterModelTest {

    private lateinit var model: SpecificCharacterContract.Model
    private lateinit var characterId: String
    private var mockedService: MarvelService = mock()
    private var validResult: Result.Success<List<Character>> = mock()
    private var invalidResult: Result.Failure = mock()

    @Before
    fun setUp() {
        characterId = TEST_CHARACTER_ID
        model = SpecificCharacterModel(mockedService)
    }

    @Test
    fun `call getSpecificCharacterFromAPI returns success result`() {
        whenever(mockedService.getSpecificCharacterFromAPI(characterId)).thenReturn(validResult)
        assertEquals(validResult, model.getSpecificCharacter(characterId))
        verify(mockedService).getSpecificCharacterFromAPI(characterId)
    }

    @Test
    fun `call getSpecificCharacterFromAPI returns failure result`() {
        whenever(mockedService.getSpecificCharacterFromAPI(characterId)).thenReturn(invalidResult)
        assertEquals(invalidResult, model.getSpecificCharacter(characterId))
        verify(mockedService).getSpecificCharacterFromAPI(characterId)
    }

    companion object{
        const val TEST_CHARACTER_ID = "1011334"
    }
}