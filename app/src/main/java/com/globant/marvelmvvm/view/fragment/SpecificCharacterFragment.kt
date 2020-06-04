package com.globant.marvelmvvm.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.globant.marvelmvvm.R
import com.globant.marvelmvvm.data.entity.Character
import com.globant.marvelmvvm.contract.SpecificCharacterContract
import com.globant.marvelmvvm.data.service.MarvelService
import com.globant.marvelmvvm.model.SpecificCharacterModel
import com.globant.marvelmvvm.util.Constants.CHARACTER_ID
import com.globant.marvelmvvm.util.Constants.EMPTY_STRING
import com.globant.marvelmvvm.util.Constants.ZERO
import com.globant.marvelmvvm.util.Data
import com.globant.marvelmvvm.util.Event
import com.globant.marvelmvvm.util.Status
import com.globant.marvelmvvm.viewmodel.SpecificCharacterViewModel
import kotlinx.android.synthetic.main.fragment_specific_character.specific_character_fragment_loader
import kotlinx.android.synthetic.main.fragment_specific_character.specific_character_fragment_name
import kotlinx.android.synthetic.main.fragment_specific_character.specific_character_fragment_description
import kotlinx.android.synthetic.main.fragment_specific_character.specific_character_fragment_character_id
import kotlinx.android.synthetic.main.fragment_specific_character.specific_character_fragment_thumbnail
import kotlinx.android.synthetic.main.fragment_specific_character.specific_character_fragment_background_image
import kotlinx.android.synthetic.main.activity_main.activity_main_toolbar
import androidx.navigation.fragment.findNavController

class SpecificCharacterFragment : Fragment() {

    private lateinit var specificCharacterViewModel: SpecificCharacterContract.ViewModel

    private inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
            }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_specific_character, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var characterId = EMPTY_STRING
        arguments?.getString(CHARACTER_ID)?.let { characterId = it }

        specificCharacterViewModel =
                ViewModelProvider(this, viewModelFactory {
                    SpecificCharacterViewModel(SpecificCharacterModel(MarvelService()), characterId)
                }).get(SpecificCharacterViewModel::class.java)

        specificCharacterViewModel.getSpecificCharacterLiveData().observe(::getLifecycle, ::updateUI)

        activity?.activity_main_toolbar?.title = getString(R.string.string_specific_character)

        specificCharacterViewModel.fetchSpecificCharacter()
    }

    private fun updateUI(data: Event<Data<List<Character>>>) {
        when (data.peekContent().status) {
            Status.LOADING -> setLoaderState(View.VISIBLE)
            Status.RESPONSE_SUCCESS -> showSpecificCharacter(data.peekContent().data)
            Status.RESPONSE_ERROR -> showError(data.peekContent().error)
        }
    }

    private fun setLoaderState(state: Int) {
        specific_character_fragment_loader.visibility = state
        specific_character_fragment_background_image.visibility = state
    }

    private fun showSpecificCharacter(data: List<Character>?) {
        setLoaderState(View.INVISIBLE)
        specific_character_fragment_thumbnail.visibility = View.VISIBLE
        specific_character_fragment_character_id.visibility = View.VISIBLE
        specific_character_fragment_name.visibility = View.VISIBLE
        specific_character_fragment_description.visibility = View.VISIBLE

        val requestOptions = RequestOptions()
                .placeholder(R.drawable.main_background)
                .error(R.drawable.error_image)

        data?.get(ZERO).let {
            specific_character_fragment_character_id.text = it?.id
            specific_character_fragment_description.text = it?.description
            specific_character_fragment_name.text = it?.name

            val thumbnail = it?.image
            context?.let { context ->
                Glide.with(context)
                        .applyDefaultRequestOptions(requestOptions)
                        .load(thumbnail)
                        .into(specific_character_fragment_thumbnail)
            }
        }
    }

    private fun showError(error: Exception?) {
        error?.let {
            Toast.makeText(this.context, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
        }
        this.findNavController().popBackStack(R.id.allCharactersFragment, false)
    }
}