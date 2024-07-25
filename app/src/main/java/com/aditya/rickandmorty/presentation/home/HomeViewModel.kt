package com.aditya.rickandmorty.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.aditya.rickandmorty.domain.Character
import com.aditya.rickandmorty.domain.GetAllCharactersUsecase
import com.aditya.rickandmorty.presentation.CharactersPagingDataSource
import com.aditya.rickandmorty.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCharactersUsecase: GetAllCharactersUsecase,
): ViewModel() {

    val characters: Flow<PagingData<Character>> =
        Pager(config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { CharactersPagingDataSource(usecase = getAllCharactersUsecase) }
        ).flow.cachedIn(viewModelScope)
}
