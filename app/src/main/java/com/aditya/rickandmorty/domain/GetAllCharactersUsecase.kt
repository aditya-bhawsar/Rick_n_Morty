package com.aditya.rickandmorty.domain

import com.aditya.rickandmorty.utils.Result
import javax.inject.Inject

class GetAllCharactersUsecase @Inject constructor(
    private val repo: ICharacterRepo
) {
    suspend operator fun invoke(): Result<List<Character>> {
        return repo.getAllCharacters()
    }
}