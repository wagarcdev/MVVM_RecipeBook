package com.arcieri.wagner.mvvm_recipebook.ui.screen.auth

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arcieri.wagner.mvvm_recipebook.navigation.Screens
import com.arcieri.wagner.mvvm_recipebook.ui.screen.auth.sign_in.SignInGoogleViewModel
import com.arcieri.wagner.mvvm_recipebook.ui.screen.auth.sign_in.SignInGoogleViewModelFactory
import com.arcieri.wagner.mvvm_recipebook.ui.screen.auth.sign_in.SingInContent
import com.arcieri.wagner.mvvm_recipebook.ui.screen.catalog.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets.BackgroundImageRow
import com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets.GradientColumn


@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AuthScreen(
    catalogViewModel: CatalogViewModel,
    signInGoogleViewModel: SignInGoogleViewModel
) {

    val context = LocalContext.current
    val state = signInGoogleViewModel.googleUser.observeAsState()
    val user = state.value

    LaunchedEffect(user) {
        if (user != null) {
            catalogViewModel.navHostController.navigate(route = Screens.MainMenuScreen.name)
        }
    }

    BackgroundImageRow()


    Column (
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) {
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
            SingInContent(signInGoogleViewModel, catalogViewModel)
        }
    }



}


@RequiresApi(Build.VERSION_CODES.R)
@Preview
@Composable
fun ScreenMainPreview() {

    val recipeBookViewModel: CatalogViewModel = viewModel()
    val signInGoogleViewModel: SignInGoogleViewModel = viewModel(
        factory = SignInGoogleViewModelFactory(LocalContext.current.applicationContext as Application)
    )

    AuthScreen(recipeBookViewModel, signInGoogleViewModel)

}