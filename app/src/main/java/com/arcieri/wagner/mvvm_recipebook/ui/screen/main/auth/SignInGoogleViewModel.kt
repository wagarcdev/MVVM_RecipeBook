package com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arcieri.wagner.mvvm_recipebook.google.GoogleUserModel
import kotlinx.coroutines.launch

class SignInGoogleViewModel (application: Application): AndroidViewModel(application) {

    private var _userState = MutableLiveData<GoogleUserModel>()
    val googleUser: LiveData<GoogleUserModel> = _userState


    private var _loadingState = MutableLiveData<GoogleUserModel>()
    val loading: LiveData<GoogleUserModel> = _loadingState

    fun fetchSignInUser(email: String?, name: String?) {
//        _loadingState.value = true

        viewModelScope.launch {
            _userState.value = GoogleUserModel(
                email = email,
                name = name
            )
        }

//        _loadingState.value = false
    }

    fun hideLoading() {
//        _loadingState.value = false
    }

    fun showLoading() {
//        _loadingState.value = true
    }

}