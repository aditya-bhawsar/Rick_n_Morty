package com.aditya.rickandmorty.data

import com.aditya.rickandmorty.domain.Character
import com.aditya.rickandmorty.domain.ICharacterRepo
import com.aditya.rickandmorty.utils.Result

class CharacterRepoImpl (private val charactersApi: CharactersApi): ICharacterRepo {
    override suspend fun getAllCharacters(): Result<List<Character>> {
        val result = charactersApi.getAllCharacters()
        return if (result.isSuccessful) {
            Result.Success(result.body()?.results ?: emptyList())
        } else {
            Result.Error()
        }
    }

    override suspend fun getCharactersBySearch(): Result<List<Character>> {
        return Result.Success(emptyList())
    }
}