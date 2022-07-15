package com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth.register.RegisterButtons
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth.sign_in.SignInButtons

@Composable
fun SingInContent() {

    val coroutineScope = rememberCoroutineScope()

    val wannaRegister = remember { mutableStateOf(false) }



    AnimatedVisibility(
        modifier = Modifier
            .fillMaxWidth(),
        visible = wannaRegister.value == false,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {

        SignInButtons(wannaRegister)
    }

    AnimatedVisibility(
        modifier = Modifier
            .fillMaxWidth(),
        visible = wannaRegister.value == true,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        RegisterButtons(wannaRegister)
    }

}


