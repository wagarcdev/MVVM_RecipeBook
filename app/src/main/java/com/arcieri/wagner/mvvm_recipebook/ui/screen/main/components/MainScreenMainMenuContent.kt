package com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import com.arcieri.wagner.mvvm_recipebook.ui.screen.catalog.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth.SignInGoogleViewModel
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth.SingInContent
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.main_menu_content.MainMenuButtons
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.main_menu_content.MainMenuButtonsScaffold

@Composable
fun MainScreenMainMenuContent(
    catalogViewModel: CatalogViewModel,
    signInGoogleViewModel: SignInGoogleViewModel
) {

    val context = LocalContext.current

    signInGoogleViewModel.checkSignedInUser(context)


//    val isSignedIn = remember { mutableStateOf(signInGoogleViewModel.googleUser.value) }

    val state = signInGoogleViewModel.googleUser.observeAsState()
    val user = state.value



    MainMenuButtonsScaffold(
        catalogViewModel = catalogViewModel,
        content = {

            AnimatedVisibility(
                visible = user != null,
                enter = EnterTransition.None,
                exit = ExitTransition.None
            ) {
                MainMenuButtons(catalogViewModel, signInGoogleViewModel)
            }

            AnimatedVisibility(
                visible = user == null,
                enter = EnterTransition.None,
                exit = ExitTransition.None
            ) {
                SingInContent(signInGoogleViewModel)
            }

        }
    )
}

