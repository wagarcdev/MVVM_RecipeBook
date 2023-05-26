package com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth
interface AuthOptionsChannel {
    object SignInWithGoogleSuccessfully : AuthOptionsChannel

    data class DisplayMessage(val resId: Int) : AuthOptionsChannel
}