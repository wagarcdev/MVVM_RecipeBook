package com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.google_one_tap_sign_in

enum class OneTapDismissCause {
    ApiIntentCanceled,
    ApiNetworkError,
    ApiUnknownCode,
    UnsupportedServices,
    UnknownError
}