package com.globant.marvelmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.globant.marvelmvvm.contract.AllCharactersContract
import com.globant.marvelmvvm.data.entity.Character
import com.globant.marvelmvvm.util.Data
import com.globant.marvelmvvm.util.Event
import com.globant.marvelmvvm.util.Result
import com.globant.marvelmvvm.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllCharactersViewModel(private val model: AllCharactersContract.Model) : ViewModel(), AllCharactersContract.ViewModel {

    var mutableMainState: MutableLiveData<Event<Data<List<Character>>>> = MutableLiveData()
    override val mainState: LiveData<Event<Data<List<Character>>>>
        get() = mutableMainState

    override fun fetchAllCharacters() = viewModelScope.launch {
        mutableMainState.postValue(Event(Data(status = Status.LOADING)))
        withContext(Dispatchers.IO) { model.getAllCharacters() }.let { result ->
            when (result) {
                is Result.Failure -> {
                    mutableMainState.postValue(
                        Event(Data(
                                status = Status.RESPONSE_ERROR,
                                error = result.exception)))
                }
                is Result.Success -> {
                    mutableMainState.postValue(
                        Event(Data(
                                status = Status.RESPONSE_SUCCESS,
                                data = result.data)))
                }
            }
        }
    }
}