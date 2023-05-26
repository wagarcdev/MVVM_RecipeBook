package com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.google_one_tap_sign_in

import androidx.compose.runtime.*

@Stable
class OneTapSignInState {
    var opened by mutableStateOf(value = false)
        private set

    fun open() {
        opened = true
    }

    fun close() {
        opened = false
    }
}

@Composable
fun rememberOneTapSignInState(): OneTapSignInState =
    remember { OneTapSignInState() }