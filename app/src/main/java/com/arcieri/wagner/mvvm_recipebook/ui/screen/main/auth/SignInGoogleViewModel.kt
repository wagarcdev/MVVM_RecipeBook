package com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.arcieri.wagner.mvvm_recipebook.google.GoogleUserModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.launch


class SignInGoogleViewModel (application: Application): AndroidViewModel(application) {

    private var _userState = MutableLiveData<GoogleUserModel>()
    val googleUser: LiveData<GoogleUserModel> = _userState


    private var _loadingState = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loadingState


    fun fetchSignInUser(email: String?, name: String?, context: Context) {
        _loadingState.value = true

        viewModelScope.launch {
            _userState.value = GoogleUserModel(
                email = email,
                name = name
            )

        }

        _loadingState.value = false
    }

    fun checkSignedInUser(applicationContext: Context) {
        _loadingState.value = true

        val gsa = GoogleSignIn.getLastSignedInAccount(applicationContext)
        if (gsa != null) {
            _userState.value = GoogleUserModel(
                email = gsa.email,
                name = gsa.displayName,
            )
        }

        _loadingState.value = false
    }


    fun hideLoading() {
        _loadingState.value = false
    }

    fun showLoading() {
        _loadingState.value = true
    }

    fun signOut(context: Context) {

        viewModelScope.launch {
            GoogleSignIn.getClient(context, GoogleSignInOptions.DEFAULT_SIGN_IN)
                .signOut()
            _userState.value = null
        }
    }

}

class SignInGoogleViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(SignInGoogleViewModel::class.java)) {
            return SignInGoogleViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}