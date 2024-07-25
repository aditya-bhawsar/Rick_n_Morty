package com.aditya.rickandmorty.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.rickandmorty.domain.Character
import com.aditya.rickandmorty.domain.GetAllCharactersUsecase
import com.aditya.rickandmorty.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCharactersUsecase: GetAllCharactersUsecase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
): ViewModel() {

    private val _allCharacters: MutableLiveData<List<Character>> = MutableLiveData(emptyList())
    val allCharacters: LiveData<List<Character>> = _allCharacters

    fun getAllCharacters() {
        viewModelScope.launch(ioDispatcher) {
            val result = getAllCharactersUsecase.invoke()
            if(result is Result.Success) {
                result.data?.let {
                    _allCharacters.postValue(it)
                }
            }
        }
    }

}
