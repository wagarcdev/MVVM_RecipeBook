package com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth

import android.app.Application
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arcieri.wagner.mvvm_recipebook.google.GoogleApiContract
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth.register.RegisterButtons
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth.sign_in.SignInButtons
import com.google.android.gms.common.api.ApiException

@Composable
fun SingInContent() {

    val coroutineScope = rememberCoroutineScope()
    val wannaRegisterState = remember { mutableStateOf(false) }

    val signInRequestCode = 1
    val context = LocalContext.current

    val signInGoogleViewModel: SignInGoogleViewModel = viewModel(
        factory = SignInGoogleViewModelFactory(context.applicationContext as Application)
    )

    val state = signInGoogleViewModel.googleUser.observeAsState()
    val user = state.value

    val isError = rememberSaveable { mutableStateOf(false) }

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = GoogleApiContract()) { task ->
            try {
                val gsa = task?.getResult(ApiException::class.java)

                if (gsa != null) {
                    signInGoogleViewModel.fetchSignInUser(gsa.email, gsa.displayName)
                } else {
                    isError.value = true
                }
            } catch (e: ApiException) {
                Log.d("Error in AuthScreen%s", e.toString())
            }
        }


    AnimatedVisibility(
        modifier = Modifier
            .fillMaxWidth(),
        visible = wannaRegisterState.value == false,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {

        SignInButtons(
            wannaRegister =  wannaRegisterState,
            onClick = { authResultLauncher.launch(signInRequestCode) },
            isError = isError.value,
            signInGoogleViewModel = signInGoogleViewModel
        )
    }

    AnimatedVisibility(
        modifier = Modifier
            .fillMaxWidth(),
        visible = wannaRegisterState.value == true,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        RegisterButtons(wannaRegisterState)
    }

}


