package com.globant.marvelmvvm.view.dialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.globant.marvelmvvm.R
import kotlinx.android.synthetic.main.dialog_insert_character_id.dialog_insert_character_id_edit_text
import kotlinx.android.synthetic.main.dialog_insert_character_id.dialog_search_button

class InsertCharacterIdDialog(private val onSearchButtonClicked: (String) -> Unit) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        activity?.layoutInflater?.inflate(R.layout.dialog_insert_character_id, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        dialog_search_button.setOnClickListener {
            val editTextCharacterId = dialog_insert_character_id_edit_text
            if (editTextCharacterId.text.toString().isNotEmpty()) {
                onSearchButtonClicked(editTextCharacterId.text.toString())
                dismiss()
            } else {
                Toast.makeText(this.context, R.string.string_insert_character_id_error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}