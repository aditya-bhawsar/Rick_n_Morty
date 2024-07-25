package com.aditya.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.aditya.rickandmorty.R
import com.aditya.rickandmorty.databinding.ItemCharacterBinding
import com.aditya.rickandmorty.domain.Character

class CharactersAdapter(
    private val itemClickListener: (Character) -> Unit,
) : PagingDataAdapter<Character, CharactersAdapter.CharacterViewHolder>(characterCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CharacterViewHolder =
        CharacterViewHolder(
            ItemCharacterBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_character,
                    parent,
                    false,
                ),
            ),
        )

    override fun onBindViewHolder(
        holder: CharacterViewHolder,
        position: Int,
    ) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class CharacterViewHolder(
        private val binding: ItemCharacterBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.apply {
                root.setOnClickListener { itemClickListener(character) }
                characterIv.load(character.image) {
                    transformations(CircleCropTransformation())
                    placeholder(R.mipmap.ic_launcher_round)
                    error(R.mipmap.ic_launcher_round)
                }
                nameTv.text = character.name
                speciesTv.text = binding.root.context.getString(R.string.species, character.species)
                statusTv.text = binding.root.context.getString(R.string.status, character.status)
            }
        }
    }
}

val characterCallback: DiffUtil.ItemCallback<Character> =
    object : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(
            oldItem: Character,
            newItem: Character,
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Character,
            newItem: Character,
        ): Boolean = oldItem == newItem
    }
