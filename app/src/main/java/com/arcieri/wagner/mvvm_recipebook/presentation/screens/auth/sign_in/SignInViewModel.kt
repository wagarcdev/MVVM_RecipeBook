package com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.data.remote.auth.AuthService
import com.arcieri.wagner.mvvm_recipebook.model.AuthError
import com.arcieri.wagner.mvvm_recipebook.model.Response
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.AuthOptionsChannel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.google_one_tap_sign_in.OneTapDismissCause
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
//         private val firebaseAuth: FirebaseAuth,
//         private val repository: RecipeBookRepository,
         private val authService: AuthService
): ViewModel() {


    private val _channel = Channel<AuthOptionsChannel>()
    val channel = _channel.receiveAsFlow()

    var userId = MutableStateFlow<String?>(null)
        private set

    init { userId.update { authService.currentUserId } }

    var userProfileImage = mutableStateOf("")


    private var _loadingState = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loadingState


    fun signInWithGoogleAccount(tokenId: String) {
        viewModelScope.launch {
            val channelElement = when (val result = authService.oneTapSignIn(tokenId = tokenId)) {
                is Response.Error -> when (result.type) {
                    AuthError.CredentialCollision -> AuthOptionsChannel.DisplayMessage(
                        resId = R.string.email_taken
                    )
                    else -> AuthOptionsChannel.DisplayMessage(
                        resId = R.string.went_wrong
                    )
                }
                else -> AuthOptionsChannel.SignInWithGoogleSuccessfully
            }

            _channel.send(element = channelElement)
        }
    }

    fun showDismiss(cause: OneTapDismissCause) {
        val resId = when (cause) {
            OneTapDismissCause.ApiIntentCanceled -> R.string.apiIntentCanceledMessage
            OneTapDismissCause.ApiNetworkError -> R.string.apiNetworkErrorMessage
            OneTapDismissCause.ApiUnknownCode -> R.string.apiUnknownCodeMessage
            OneTapDismissCause.UnsupportedServices -> R.string.unsupportedServicesMessage
            OneTapDismissCause.UnknownError -> R.string.unknownErrorMessage
        }

        viewModelScope.launch {
            _channel.send(element = AuthOptionsChannel.DisplayMessage(resId = resId))
        }
    }

    fun hideLoading() {
        _loadingState.value = false
    }

    fun showLoading() {
        _loadingState.value = true
    }

    fun signOut(context: Context) {

        // TODO signOut

    }

}
