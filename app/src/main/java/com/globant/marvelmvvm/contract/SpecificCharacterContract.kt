package com.globant.marvelmvvm.contract

import androidx.lifecycle.LiveData
import com.globant.domain.entity.Character
import com.globant.marvelmvvm.util.Data
import com.globant.marvelmvvm.util.Event
import com.globant.domain.util.Result
import kotlinx.coroutines.Job

interface SpecificCharacterContract {
    interface ViewModel {
        fun getSpecificCharacterLiveData(): LiveData<Event<Data<List<Character>>>>
        fun fetchSpecificCharacter(): Job
    }

    interface Model {
        fun getSpecificCharacter(characterId: String): Result<List<Character>>
    }
}