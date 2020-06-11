package com.globant.marvelmvvm.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.globant.marvelmvvm.R
import com.globant.domain.entity.Character
import com.globant.marvelmvvm.util.AllCharactersRecyclerViewAdapter
import com.globant.domain.util.Constants.CHARACTER_ID
import com.globant.marvelmvvm.util.Data
import com.globant.marvelmvvm.util.Event
import com.globant.marvelmvvm.util.Status
import com.globant.marvelmvvm.viewmodel.AllCharactersViewModel
import kotlinx.android.synthetic.main.activity_main.activity_main_toolbar
import kotlinx.android.synthetic.main.fragment_all_characters.fragment_all_characters_background_image
import kotlinx.android.synthetic.main.fragment_all_characters.fragment_all_characters_loader
import kotlinx.android.synthetic.main.fragment_all_characters.fragment_all_characters_recycler_view
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCharactersFragment : Fragment() {

    private val allCharactersViewModel by viewModel<AllCharactersViewModel>()
    private var allCharactersAdapter = AllCharactersRecyclerViewAdapter { characterId ->
        replaceFragment(characterId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_all_characters, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allCharactersViewModel.getAllCharactersLiveData().observe(::getLifecycle, ::updateUI)

        activity?.activity_main_toolbar?.title = getString(R.string.string_all_characters)

        allCharactersViewModel.fetchAllCharacters()
    }

    private fun updateUI(data: Event<Data<List<Character>>>) {
        when (data.peekContent().status) {
            Status.LOADING -> setLoaderState(View.VISIBLE)
            Status.RESPONSE_SUCCESS -> showAllCharacters(data.peekContent().data)
            Status.RESPONSE_ERROR -> showError()
        }
    }

    private fun setLoaderState(state: Int) {
        fragment_all_characters_loader.visibility = state
        fragment_all_characters_background_image.visibility = state
    }

    private fun showAllCharacters(data: List<Character>?) {
        data?.let {
            setLoaderState(View.GONE)
            allCharactersAdapter.submitList(it)
            fragment_all_characters_recycler_view.apply {
                layoutManager = LinearLayoutManager(this.context)
                adapter = allCharactersAdapter
                visibility = View.VISIBLE
            }
        }
    }

    private fun showError() {
        Toast.makeText(this.context, getString(R.string.string_request_error), Toast.LENGTH_SHORT).show()
        fragment_all_characters_loader.visibility = View.INVISIBLE
    }

    private fun replaceFragment(characterId: String) {
        val args = Bundle()
        args.putString(CHARACTER_ID, characterId)
        this.findNavController().navigate(R.id.specificCharacterFragment, args)
    }
}