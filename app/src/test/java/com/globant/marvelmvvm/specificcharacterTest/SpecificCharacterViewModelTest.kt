package com.globant.marvelmvvm.specificcharacterTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.globant.marvelmvvm.contract.SpecificCharacterContract
import com.globant.marvelmvvm.util.Data
import com.globant.marvelmvvm.util.Status
import com.globant.domain.util.Result
import com.globant.domain.entity.Character
import com.globant.marvelmvvm.viewmodel.SpecificCharacterViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SpecificCharacterViewModelTest {

    @ObsoleteCoroutinesApi
    private var mainThreadSurrogate = newSingleThreadContext(UI_THREAD)

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SpecificCharacterContract.ViewModel
    private lateinit var responseList: List<Data<out List<Character>>>
    private lateinit var characterId: String
    private var mockedModel: SpecificCharacterContract.Model = mock()
    private var validResult: Result.Success<List<Character>> = mock()
    private var charactersResponse: List<Character> = mock()
    private var invalidResult: Result.Failure = mock()
    private var exception: Exception = mock()

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        characterId = TEST_CHARACTER_ID
        viewModel = SpecificCharacterViewModel(mockedModel, characterId)
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @After
    fun after() {
        mainThreadSurrogate.close()
        Dispatchers.resetMain()
    }

    @Test
    fun `when getSpecificCharacter gets error in response`() {
        val liveDataUnderTest = viewModel.getSpecificCharacterLiveData().testObserver()
        val responseList = listOf(
            Data(status = Status.LOADING, data = null, error = null),
            Data(status = Status.RESPONSE_ERROR, data = null, error = exception)
        )
        whenever(mockedModel.getSpecificCharacter(characterId)).thenReturn(invalidResult)
        whenever(invalidResult.exception).thenReturn(exception)
        runBlocking {
            viewModel.fetchSpecificCharacter().join()
        }
        verify(mockedModel).getSpecificCharacter(characterId)
        assertEquals(responseList[ZERO].status, liveDataUnderTest.observedValues[ZERO]?.peekContent()?.status)
        assertEquals(responseList[ONE].status, liveDataUnderTest.observedValues[ONE]?.peekContent()?.status)
        assertEquals(responseList[ONE].error, liveDataUnderTest.observedValues[ONE]?.peekContent()?.error)
    }

    @Test
    fun `when getSpecificCharacter returns success response`() {
        val liveDataUnderTest = viewModel.getSpecificCharacterLiveData().testObserver()
        responseList = listOf(
            Data(status = Status.LOADING, data = null, error = null),
            Data(status = Status.RESPONSE_SUCCESS, data = charactersResponse, error = null)
        )
        whenever(mockedModel.getSpecificCharacter(characterId)).thenReturn(validResult)
        whenever(validResult.data).thenReturn(charactersResponse)
        runBlocking {
            viewModel.fetchSpecificCharacter().join()
        }
        verify(mockedModel).getSpecificCharacter(characterId)
        assertEquals(responseList[ZERO].status, liveDataUnderTest.observedValues[ZERO]?.peekContent()?.status)
        assertEquals(responseList[ONE].status, liveDataUnderTest.observedValues[ONE]?.peekContent()?.status)
        assertEquals(responseList[ONE].data, liveDataUnderTest.observedValues[ONE]?.peekContent()?.data)
    }


    class TestObserver<T> : Observer<T> {
        val observedValues = mutableListOf<T?>()

        override fun onChanged(value: T?) {
            observedValues.add(value)
        }
    }

    private fun <T> LiveData<T>.testObserver() = TestObserver<T>().also { observeForever(it) }

    companion object {
        private const val UI_THREAD = "UI thread"
        private const val ZERO = 0
        private const val ONE = 1
        private const val TEST_CHARACTER_ID = "1011334"
    }
}