package com.aditya.rickandmorty.data

import com.aditya.rickandmorty.domain.Character
import com.aditya.rickandmorty.domain.CharacterResponse
import com.aditya.rickandmorty.domain.ICharacterRepo
import com.aditya.rickandmorty.utils.Result

class CharacterRepoImpl (private val charactersApi: CharactersApi): ICharacterRepo {

    override suspend fun getAllCharacters(
        pageNumber: Int,
        name: String,
    ): Result<CharacterResponse> {
        val result = charactersApi.getAllCharacters(
            page = pageNumber,
            name = name
        )
        return if (result.isSuccessful) {
            result.body()?.let {
                Result.Success(it)
            } ?: Result.Error(message = "No Data Returned")
        } else {
            Result.Error(message = "API Request Failed")
        }
    }
}