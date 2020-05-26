package com.globant.marvelmvvm.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.globant.marvelmvvm.R
import com.globant.marvelmvvm.util.Constants.CHARACTER_ID
import com.globant.marvelmvvm.util.Constants.EMPTY_STRING
import kotlinx.android.synthetic.main.fragment_specific_character.specific_character_fragment_character_id

class SpecificCharacterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_specific_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var characterId = EMPTY_STRING
        arguments?.getString(CHARACTER_ID)?.let { characterId = it }
        specific_character_fragment_character_id.text = characterId
        //TODO: I WILL USE THIS TO MAKE THE REQUEST TO THE API IN THE NEXT PR
    }
}