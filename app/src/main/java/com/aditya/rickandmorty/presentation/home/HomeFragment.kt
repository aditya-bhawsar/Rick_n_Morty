package com.aditya.rickandmorty.presentation.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.aditya.rickandmorty.R
import com.aditya.rickandmorty.databinding.FragmentHomeBinding
import com.aditya.rickandmorty.presentation.adapter.CharactersAdapter
import com.aditya.rickandmorty.presentation.adapter.PagingLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        charactersAdapter =
            CharactersAdapter { character ->
                findNavController().navigate(HomeFragmentDirections.actionHomeToDetails(character))
            }

        binding?.apply {
            listRv.adapter =
                charactersAdapter?.withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter { charactersAdapter?.retry() },
                    footer = PagingLoadStateAdapter { charactersAdapter?.retry() },
                )
        }

        setUpObservers()

        binding?.searchEt?.doAfterTextChanged {
            resetPagination(it.toString())
        }
    }

    private fun setUpObservers() {
        resetPagination()

        lifecycleScope.launch {
            charactersAdapter?.loadStateFlow?.collectLatest {
                binding?.apply {
                    val showLoading = it.refresh is LoadState.Loading
                    loadCv.isVisible = showLoading
                    listRv.isVisible = !showLoading

                    val showEmpty =
                        it.refresh is LoadState.NotLoading && charactersAdapter != null && charactersAdapter?.itemCount == 0
                    noItemCv.isVisible = showEmpty

                    val showError = it.refresh is LoadState.Error
                    if (showError) {
                        errorCv.isVisible = true
                        retryBtn.setOnClickListener { charactersAdapter?.retry() }
                    } else {
                        errorCv.isGone = true
                    }
                    listRv.isVisible = !showLoading && !showError && !showEmpty
                }
            }
        }
    }

    private fun resetPagination(searchQuery: String = "") {
        binding?.listRv?.scrollToPosition(0)
        viewModel.characters?.let {
            if (it.hasObservers()) it.removeObservers(viewLifecycleOwner)
        }
        viewModel.createPaginator(searchQuery)
        viewModel.characters?.observe(viewLifecycleOwner) {
            charactersAdapter?.submitData(lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
