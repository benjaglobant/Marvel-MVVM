package com.globant.marvelmvvm.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.globant.marvelmvvm.R
import com.globant.marvelmvvm.data.entity.Character
import kotlinx.android.synthetic.main.card_view_character_item_layout.view.all_characters_item_id
import kotlinx.android.synthetic.main.card_view_character_item_layout.view.all_characters_item_thumbnail

class AllCharactersRecyclerViewAdapter(private val onItemClick: (String) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var characterList: List<Character> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WeatherViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view_character_item_layout, parent, false), onItemClick)}

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WeatherViewHolder).bind(this.characterList[position])
    }

    override fun getItemCount(): Int = this.characterList.size

    fun submitList(characters: List<Character>) {
        this.characterList = characters
    }

    class WeatherViewHolder(itemView: View, private val onItemClick: (String) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private val characterThumbnail = itemView.all_characters_item_thumbnail
        private val characterId = itemView.all_characters_item_id

        fun bind(character: Character) {
            itemView.setOnClickListener{
                onItemClick(character.id)
            }
            character.apply {
                characterId.text = this.id

                val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

                Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(character.image)
                    .into(characterThumbnail)
            }
        }
    }
}