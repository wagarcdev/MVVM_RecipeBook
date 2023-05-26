package com.arcieri.wagner.mvvm_recipebook.model

interface Response<out D, out T> {
    data class Success<D>(
        val data: D
    ) : Response<D, Nothing>

    data class Error<T>(
        val type: T
    ) : Response<Nothing, T>
}