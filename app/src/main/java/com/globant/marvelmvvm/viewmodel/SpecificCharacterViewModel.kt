package com.globant.marvelmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.globant.marvelmvvm.contract.SpecificCharacterContract
import com.globant.domain.entity.Character
import com.globant.domain.util.Result
import com.globant.marvelmvvm.util.Data
import com.globant.marvelmvvm.util.Event
import com.globant.marvelmvvm.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpecificCharacterViewModel(private val model: SpecificCharacterContract.Model, private val characterId: String): ViewModel(), SpecificCharacterContract.ViewModel {

    private var mutableMainState: MutableLiveData<Event<Data<List<Character>>>> = MutableLiveData()
    override fun getSpecificCharacterLiveData(): LiveData<Event<Data<List<Character>>>> = mutableMainState

    override fun fetchSpecificCharacter()= viewModelScope.launch {
        mutableMainState.postValue(Event(Data(status = Status.LOADING)))
        withContext(Dispatchers.IO) { model.getSpecificCharacter(characterId) }.let { result ->
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