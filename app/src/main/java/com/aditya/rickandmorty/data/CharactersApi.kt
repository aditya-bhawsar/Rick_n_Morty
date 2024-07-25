package com.aditya.rickandmorty.data

import com.aditya.rickandmorty.domain.Character
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {

    @GET("/character")
    suspend fun getAllCharacters(): Response<Character>
}