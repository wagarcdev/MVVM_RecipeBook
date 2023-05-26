package com.arcieri.wagner.mvvm_recipebook.data.remote.auth

import com.arcieri.wagner.mvvm_recipebook.model.AuthError
import com.arcieri.wagner.mvvm_recipebook.model.Response

interface AuthService {

    val currentUserId: String?

    val isUserAnonymous: Boolean


    suspend fun createAnonymousAccount()

    suspend fun oneTapSignIn(tokenId: String): Response<Unit, AuthError>

    suspend fun signIn(
        email: String,
        password: String
    ): Response<Unit, AuthError>

    suspend fun signUp(
        email: String,
        password: String
    ): Response<Unit, AuthError>

    suspend fun sendRecoverPasswordEmail(email: String): Response<Unit, AuthError>

    suspend fun signOut()


}