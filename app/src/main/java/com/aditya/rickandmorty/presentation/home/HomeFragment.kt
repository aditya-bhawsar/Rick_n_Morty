package com.aditya.rickandmorty.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aditya.rickandmorty.R
import com.aditya.rickandmorty.databinding.FragmentHomeBinding
import com.aditya.rickandmorty.presentation.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    private var binding: FragmentHomeBinding? = null
    private var charactersAdapter: CharactersAdapter? = null

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        charactersAdapter = CharactersAdapter()

        binding?.apply {
            listRv.adapter = charactersAdapter
        }

        viewModel.allCharacters.observe(viewLifecycleOwner) {
            charactersAdapter?.differ?.submitList(it)
        }

        viewModel.getAllCharacters()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
