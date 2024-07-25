package com.aditya.rickandmorty.data

import com.aditya.rickandmorty.domain.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {
    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int = 1,
        @Query("name") name: String,
    ): Response<CharacterResponse>

}
