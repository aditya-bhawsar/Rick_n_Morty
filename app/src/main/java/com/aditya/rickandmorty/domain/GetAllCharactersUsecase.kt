package com.aditya.rickandmorty.domain

import com.aditya.rickandmorty.utils.Result
import javax.inject.Inject

class GetAllCharactersUsecase @Inject constructor(
    private val repo: ICharacterRepo
) {
    suspend operator fun invoke(pageNumber: Int, name: String = ""): Result<CharacterResponse> {
        return repo.getAllCharacters(pageNumber, name)
    }
}