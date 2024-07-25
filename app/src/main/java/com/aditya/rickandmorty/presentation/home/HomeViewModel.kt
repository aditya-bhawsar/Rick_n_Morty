package com.aditya.rickandmorty.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.aditya.rickandmorty.domain.Character
import com.aditya.rickandmorty.domain.GetAllCharactersUsecase
import com.aditya.rickandmorty.presentation.adapter.CharactersPagingDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCharactersUsecase: GetAllCharactersUsecase,
): ViewModel() {

    var characters: LiveData<PagingData<Character>>? = null

    fun createPaginator(searchQuery: String = "") {
        characters = Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { CharactersPagingDataSource(
                usecase = getAllCharactersUsecase,
                searchQuery = searchQuery
            ) }
        ).liveData.cachedIn(viewModelScope)
    }
}
