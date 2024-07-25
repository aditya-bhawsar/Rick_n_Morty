package com.aditya.rickandmorty.domain

import com.aditya.rickandmorty.utils.Result

interface ICharacterRepo {
    suspend fun getAllCharacters(pageNumber: Int, name: String): Result<CharacterResponse>
}