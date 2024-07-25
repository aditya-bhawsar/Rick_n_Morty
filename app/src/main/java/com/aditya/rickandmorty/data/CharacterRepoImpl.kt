package com.aditya.rickandmorty.data

import com.aditya.rickandmorty.domain.Character
import com.aditya.rickandmorty.domain.ICharacterRepo
import com.aditya.rickandmorty.utils.Result

class CharacterRepoImpl (charactersApi: CharactersApi): ICharacterRepo {
    override fun getAllCharacters(): Result<List<Character>> {
        return Result.Success(emptyList())
    }

    override fun getCharacterByID(): Result<Character> {
        return Result.Success(Character(id = 0))
    }

    override fun getCharactersBySearch(): Result<List<Character>> {
        return Result.Success(emptyList())
    }
}