package com.aditya.rickandmorty.domain

import com.aditya.rickandmorty.utils.Result

interface ICharacterRepo {
    suspend fun getAllCharacters(): Result<List<Character>>
    suspend fun getCharactersBySearch(): Result<List<Character>>
}