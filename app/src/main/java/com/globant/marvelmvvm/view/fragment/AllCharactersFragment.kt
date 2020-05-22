package com.globant.marvelmvvm.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.globant.marvelmvvm.R
import com.globant.marvelmvvm.contract.AllCharactersContract
import com.globant.marvelmvvm.data.entity.Character
import com.globant.marvelmvvm.data.service.MarvelService
import com.globant.marvelmvvm.model.AllCharactersModel
import com.globant.marvelmvvm.util.Constants.ZERO
import com.globant.marvelmvvm.util.Data
import com.globant.marvelmvvm.util.Event
import com.globant.marvelmvvm.util.Status
import com.globant.marvelmvvm.viewmodel.AllCharactersViewModel
import kotlinx.android.synthetic.main.fragment_all_characters.fragment_all_characters_background_image
import kotlinx.android.synthetic.main.fragment_all_characters.fragment_all_characters_character_id
import kotlinx.android.synthetic.main.fragment_all_characters.fragment_all_characters_count
import kotlinx.android.synthetic.main.fragment_all_characters.fragment_all_characters_loader

class AllCharactersFragment : Fragment() {

    private lateinit var allCharactersViewModel: AllCharactersContract.ViewModel

    private inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(aClass: Class<T>):T = f() as T
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_all_characters, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allCharactersViewModel =
            ViewModelProvider(this, viewModelFactory {
                AllCharactersViewModel(AllCharactersModel(MarvelService())) })
                    .get(AllCharactersViewModel::class.java)

        allCharactersViewModel.mainState.observe(::getLifecycle, ::updateUI)

        allCharactersViewModel.fetchAllCharacters()
    }

    private fun updateUI(data: Event<Data<List<Character>>>) {
        when(data.peekContent().status){
            Status.LOADING -> setLoaderState(View.VISIBLE)
            Status.RESPONSE_SUCCESS -> showAllCharacters(data.peekContent().data)
            Status.RESPONSE_ERROR -> showError()
        }
    }

    private fun setLoaderState(state: Int){
        fragment_all_characters_loader.visibility = state
        fragment_all_characters_background_image.visibility = state
    }

    private fun showAllCharacters(data: List<Character>?) {
        setLoaderState(View.GONE)
        data.let {
            fragment_all_characters_character_id.text =
                "${getString(R.string.string_first_character_id)} ${it?.get(ZERO)?.id}"
            fragment_all_characters_count.text =
                "${getString(R.string.string_number_of_characters)} ${it?.size}"
        }
    }

    private fun showError(){
        Toast.makeText(this.context, getString(R.string.string_request_error), Toast.LENGTH_SHORT).show()
    }
}