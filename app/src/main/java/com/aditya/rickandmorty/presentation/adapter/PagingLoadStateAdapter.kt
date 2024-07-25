package com.aditya.rickandmorty.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aditya.rickandmorty.R
import com.aditya.rickandmorty.databinding.ItemPagingHeaderFooterBinding

class PagingLoadStateAdapter(
    private val retryListener: () -> Unit,
) : LoadStateAdapter<PagingLoadStateAdapter.LoadStateView>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ) = LoadStateView(
        ItemPagingHeaderFooterBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_paging_header_footer, parent, false),
        ),
    )

    override fun onBindViewHolder(
        holder: LoadStateView,
        loadState: LoadState,
    ) = holder.bind(loadState)

    inner class LoadStateView(val binding: ItemPagingHeaderFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                retryBtn.isVisible = loadState is LoadState.Error
                errorMsgTv.isVisible = loadState is LoadState.Error
                retryBtn.setOnClickListener { retryListener() }
            }
        }
    }
}
