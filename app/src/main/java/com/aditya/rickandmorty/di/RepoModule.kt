package com.aditya.rickandmorty.di

import com.aditya.rickandmorty.data.CharacterRepoImpl
import com.aditya.rickandmorty.data.CharactersApi
import com.aditya.rickandmorty.domain.ICharacterRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun getCharacterRepo(
        charactersApi: CharactersApi,
    ): ICharacterRepo {
        return CharacterRepoImpl(charactersApi)
    }

}