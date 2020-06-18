package com.globant.marvelmvvm.allcharactersTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.globant.marvelmvvm.contract.AllCharactersContract
import com.globant.domain.entity.Character
import com.globant.marvelmvvm.util.Data
import com.globant.domain.util.Result
import com.globant.marvelmvvm.util.Status
import com.globant.marvelmvvm.viewmodel.AllCharactersViewModel
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
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class AllCharactersViewModelTest {

    @ObsoleteCoroutinesApi
    private var mainThreadSurrogate = newSingleThreadContext(UI_THREAD)

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AllCharactersContract.ViewModel
    private lateinit var responseList: List<Data<out List<Character>>>
    private var mockedModel: AllCharactersContract.Model = mock()
    private var validResult: Result.Success<List<Character>> = mock()
    private var charactersResponse: List<Character> = mock()
    private var invalidResult: Result.Failure = mock()
    private var exception: Exception = mock()

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = AllCharactersViewModel(mockedModel)
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @After
    fun after() {
        mainThreadSurrogate.close()
        Dispatchers.resetMain()
    }

    @Test
    fun `when getAllCharacters returns success response`() {
        val liveDataUnderTest = viewModel.getAllCharactersLiveData().testObserver()
        responseList = listOf(
            Data(status = Status.LOADING, data = null, error = null),
            Data(status = Status.RESPONSE_SUCCESS, data = charactersResponse, error = null)
        )
        whenever(mockedModel.getAllCharacters()).thenReturn(validResult)
        whenever(validResult.data).thenReturn(charactersResponse)

        runBlocking {
            viewModel.fetchAllCharacters().join()
        }

        verify(mockedModel).getAllCharacters()

        assertEquals(responseList[ZERO].status, liveDataUnderTest.observedValues[ZERO]?.peekContent()?.status)
        assertEquals(responseList[ONE].status, liveDataUnderTest.observedValues[ONE]?.peekContent()?.status)
        assertEquals(responseList[ONE].data, liveDataUnderTest.observedValues[ONE]?.peekContent()?.data)
    }

    @Test
    fun `when getAllCharacters returns gets error in response`() {
        val liveDataUnderTest = viewModel.getAllCharactersLiveData().testObserver()
        val responseList = listOf(
            Data(status = Status.LOADING, data = null, error = null),
            Data(status = Status.RESPONSE_ERROR, data = null, error = exception)
        )
        whenever(mockedModel.getAllCharacters()).thenReturn(invalidResult)
        whenever(invalidResult.exception).thenReturn(exception)

        runBlocking {
            viewModel.fetchAllCharacters().join()
        }

        verify(mockedModel).getAllCharacters()

        assertEquals(responseList[ZERO].status, liveDataUnderTest.observedValues[ZERO]?.peekContent()?.status)
        assertEquals(responseList[ONE].status, liveDataUnderTest.observedValues[ONE]?.peekContent()?.status)
        assertEquals(responseList[ONE].error, liveDataUnderTest.observedValues[ONE]?.peekContent()?.error)
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
    }
}
