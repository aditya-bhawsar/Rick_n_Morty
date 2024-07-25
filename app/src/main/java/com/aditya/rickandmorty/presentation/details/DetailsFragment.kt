package com.aditya.rickandmorty.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.aditya.rickandmorty.R
import com.aditya.rickandmorty.databinding.FragmentDetailsBinding
import com.aditya.rickandmorty.databinding.FragmentHomeBinding
import com.aditya.rickandmorty.presentation.home.HomeViewModel

class DetailsFragment: Fragment(R.layout.fragment_details) {

    private val args by navArgs<DetailsFragmentArgs>()

    private var binding: FragmentDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        binding?.apply {
            characterIv.load(args.character.image) {
                placeholder(R.mipmap.ic_launcher_round)
                error(R.mipmap.ic_launcher_round)
            }
            nameTv.text = args.character.name
            speciesTv.text = args.character.species.ifBlank { getString(R.string.unknown) }
            statusTv.text = args.character.status.ifBlank { getString(R.string.unknown) }
            genderTv.text = args.character.gender.ifBlank { getString(R.string.unknown) }
            typeTv.text = args.character.type.ifBlank { getString(R.string.none) }
        }
    }
}