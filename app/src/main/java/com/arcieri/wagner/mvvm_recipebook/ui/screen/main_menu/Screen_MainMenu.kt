package com.arcieri.wagner.mvvm_recipebook.ui.screen.main_menu

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.navigation.Screens
import com.arcieri.wagner.mvvm_recipebook.ui.screen.auth.components.main_menu_content.MainMenuButtons
import com.arcieri.wagner.mvvm_recipebook.ui.screen.auth.sign_in.SignInGoogleViewModel
import com.arcieri.wagner.mvvm_recipebook.ui.screen.catalog.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets.BackgroundImageRow
import com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets.GradientColumn

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MainMenu(
    catalogViewModel: CatalogViewModel,
    signInGoogleViewModel: SignInGoogleViewModel
) {


    val state = signInGoogleViewModel.googleUser.observeAsState()
    val user = state.value

    LaunchedEffect(user) {
        if (user == null) {
            catalogViewModel.navHostController.navigate(route = Screens.AuthScreen.name)
        }
    }

    BackgroundImageRow()

    GradientColumn(
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize(),
        columnHorizontalAlignment = Alignment.CenterHorizontally,
        columnVerticalArrangement = Arrangement.Bottom ,
        topSpacerTransition = true,
        topSpacerTransitionHeight = 50.dp,
        topTransitionTopColor = Color(0x00000000),
        topTransitionMiddleColor = Color(0x33000000),
        topTransitionBottomColor = Color(0xCC000000),
        bottomSpacerTransition = false,
        columnTopColor = Color(0xCC000000),
        columnMiddleColor = Color(0xFA000000),
        columnBottomColor = Color(0xFA000000),
        contentHorizontalAlignment = Alignment.CenterHorizontally,
        contentVerticalArrangement =  Arrangement.SpaceBetween,
    ) {
        MainMenuButtons(catalogViewModel, signInGoogleViewModel)
    }

}