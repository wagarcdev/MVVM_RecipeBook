package com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.arcieri.wagner.mvvm_recipebook.ui.screen.catalog.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth.SingInContent
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.main_menu_content.MainMenuButtons
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.main_menu_content.MainMenuButtonsScaffold

@Composable
fun MainScreenMainMenuContent(catalogViewModel: CatalogViewModel) {

    val signedUser = remember { mutableStateOf(false) }

    MainMenuButtonsScaffold(
        catalogViewModel = catalogViewModel,
        content = {

            AnimatedVisibility(
                visible = signedUser.value == true,
                enter = EnterTransition.None,
                exit = ExitTransition.None
            ) {
                MainMenuButtons(catalogViewModel)
            }

            AnimatedVisibility(
                visible = signedUser.value == false,
                enter = EnterTransition.None,
                exit = ExitTransition.None
            ) {
                SingInContent()
            }

        }
    )
}

