package com.aditya.rickandmorty.presentation.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aditya.rickandmorty.domain.Character
import com.aditya.rickandmorty.domain.GetAllCharactersUsecase

class CharactersPagingDataSource(
    private val usecase: GetAllCharactersUsecase,
    private val searchQuery: String = "",
) : PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1
        return try {
            val result = usecase(pageNumber, searchQuery)
            val data = result.data

            LoadResult.Page(
                data = data?.results.orEmpty(),
                prevKey = null,
                nextKey = if (result.data?.results?.isEmpty() == true) null else pageNumber + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? = null
}
