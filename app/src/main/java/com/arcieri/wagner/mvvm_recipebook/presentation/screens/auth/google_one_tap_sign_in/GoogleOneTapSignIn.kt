package com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.google_one_tap_sign_in

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.UnsupportedApiCallException

@Composable
fun GoogleOneTapSignIn(
    state: OneTapSignInState,
    clientId: String,
    onTokenIdReceived: (String) -> Unit,
    onDialogDismissed: (OneTapDismissCause) -> Unit
) {
    val activity = LocalContext.current as Activity

    val activityResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                val oneTapClient = Identity.getSignInClient(activity)
                val credentials = oneTapClient.getSignInCredentialFromIntent(result.data)
                val tokenId = credentials.googleIdToken

                if (tokenId != null) onTokenIdReceived(tokenId)
                else onDialogDismissed(OneTapDismissCause.UnknownError)

                state.close()
            }
            else -> {
                onDialogDismissed(OneTapDismissCause.UnknownError)
                state.close()
            }
        }
    }

    LaunchedEffect(key1 = state.opened) {
        if (state.opened) {
            signIn(
                activity = activity,
                clientId = clientId,
                launchActivityResult = { intentSenderRequest ->
                    activityResultLauncher.launch(intentSenderRequest)
                },
                onDismissCause = { cause ->
                    onDialogDismissed(cause)
                    state.close()
                }
            )
        }
    }
}

private fun signIn(
    activity: Activity,
    clientId: String,
    launchActivityResult: (IntentSenderRequest) -> Unit,
    onDismissCause: (OneTapDismissCause) -> Unit
) {
    val signInClient = Identity.getSignInClient(activity)

    val idTokenRequestOptions = BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
        .setSupported(true)
        .setFilterByAuthorizedAccounts(true)
        .setServerClientId(clientId)
        .build()

    val passwordRequestOptions = BeginSignInRequest.PasswordRequestOptions.builder()
        .setSupported(true)
        .build()

    val signInRequest = BeginSignInRequest.builder()
        .setPasswordRequestOptions(passwordRequestOptions)
        .setGoogleIdTokenRequestOptions(idTokenRequestOptions)
        .setAutoSelectEnabled(true)
        .build()

    signInClient.beginSignIn(signInRequest)
        .addOnSuccessListener { result ->
            launchActivityResult(
                IntentSenderRequest.Builder(
                    result.pendingIntent.intentSender
                ).build()
            )
        }
        .addOnFailureListener { exception ->
            exception.printStackTrace()

            if (exception is UnsupportedApiCallException) {
                onDismissCause(OneTapDismissCause.UnsupportedServices)
                return@addOnFailureListener
            }

            signUp(
                activity = activity,
                clientId = clientId,
                launchActivityResult = launchActivityResult,
                onDismissCause = onDismissCause
            )
        }
}

private fun signUp(
    activity: Activity,
    clientId: String,
    launchActivityResult: (IntentSenderRequest) -> Unit,
    onDismissCause: (OneTapDismissCause) -> Unit
) {
    val signInClient = Identity.getSignInClient(activity)

    val idTokenRequestOptions = BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
        .setSupported(true)
        .setFilterByAuthorizedAccounts(false)
        .setServerClientId(clientId)
        .build()

    val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(idTokenRequestOptions)
        .build()

    signInClient.beginSignIn(signInRequest)
        .addOnSuccessListener { result ->
            launchActivityResult(
                IntentSenderRequest.Builder(
                    result.pendingIntent.intentSender
                ).build()
            )
        }
        .addOnFailureListener { exception ->
            exception.printStackTrace()

            val cause = when (exception) {
                is ApiException -> when (exception.statusCode) {
                    CommonStatusCodes.CANCELED -> OneTapDismissCause.ApiIntentCanceled
                    CommonStatusCodes.NETWORK_ERROR -> OneTapDismissCause.ApiNetworkError
                    else -> OneTapDismissCause.ApiUnknownCode
                }
                else -> OneTapDismissCause.UnknownError
            }

            onDismissCause(cause)
        }
}