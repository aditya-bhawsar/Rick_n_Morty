package com.aditya.rickandmorty.utils

sealed class Result<T>(
    val data: T? = null,
) {
    class Success<T>(
        data: T,
    ) : Result<T>(data)

    class Error<T>(
        data: T? = null,
        val message: String? = null,
        val throwable: Throwable? = null,
    ) : Result<T>(data)
}
