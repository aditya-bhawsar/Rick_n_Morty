package com.aditya.rickandmorty.domain

import com.aditya.rickandmorty.utils.Result

interface ICharacterRepo {
    fun getAllCharacters(): Result<List<Character>>
    fun getCharacterByID(): Result<Character>
    fun getCharactersBySearch(): Result<List<Character>>
}