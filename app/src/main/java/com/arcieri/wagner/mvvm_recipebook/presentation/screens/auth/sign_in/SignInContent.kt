package com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.AuthOptionsChannel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.google_one_tap_sign_in.GoogleOneTapSignIn
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.google_one_tap_sign_in.rememberOneTapSignInState
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.forgot.ForgotPasswordButtons
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.register.RegisterButtons
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.sign_in.SignInButtons
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.CatalogViewModel

@Composable
fun SingInContent(
    signInGoogleViewModel: SignInViewModel,
    viewModel: CatalogViewModel,
    onNavigateToHome: () -> Unit,
) {

    val wannaRegisterState = remember { mutableStateOf(false) }
    val forgotPassword = remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }


    val context = LocalContext.current

    val isError = rememberSaveable { mutableStateOf(false) }

    val oneTapSignInState = rememberOneTapSignInState()


    GoogleOneTapSignIn(
        state = oneTapSignInState,
        clientId = stringResource(id = com.arcieri.wagner.mvvm_recipebook.R.string.google_id),
        onTokenIdReceived = signInGoogleViewModel::signInWithGoogleAccount,
        onDialogDismissed = signInGoogleViewModel::showDismiss
    )

    //TODO auto-login not working
    LaunchedEffect(Unit) {
        signInGoogleViewModel.channel.collect { channel ->
            when (channel) {
                is AuthOptionsChannel.SignInWithGoogleSuccessfully -> onNavigateToHome()
                is AuthOptionsChannel.DisplayMessage -> snackbarHostState.showSnackbar(
                    message = context.getString(channel.resId),
                    withDismissAction = true
                )
            }
        }
    }





    AnimatedVisibility(
        modifier = Modifier
            .fillMaxWidth(),
        visible = !wannaRegisterState.value && !forgotPassword.value,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {

        SignInButtons(
            wannaRegister =  wannaRegisterState,
            forgotPassword = forgotPassword,
            oneTapSignInState = oneTapSignInState,
            isError = isError.value,
            signInGoogleViewModel = signInGoogleViewModel
        )
    }

    AnimatedVisibility(
        modifier = Modifier
            .fillMaxWidth(),
        visible = wannaRegisterState.value,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        RegisterButtons(wannaRegisterState)
    }

    AnimatedVisibility(
        modifier = Modifier
            .fillMaxWidth(),
        visible = forgotPassword.value,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        ForgotPasswordButtons(forgotPassword)
    }



}


