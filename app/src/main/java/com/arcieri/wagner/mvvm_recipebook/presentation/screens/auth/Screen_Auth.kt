package com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.SignInViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.SingInContent
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.BackgroundImageRow
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.GradientColumn


@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AuthScreen(
    catalogViewModel: CatalogViewModel,
    signInGoogleViewModel: SignInViewModel,
    onNavigateToHome: () -> Unit
) {

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
            topTransitionMiddleColor = Color(0x40000000),
            topTransitionBottomColor = Color(0xB3000000),
            bottomSpacerTransition = false,
            columnTopColor = Color(0xB3000000),
            columnMiddleColor = Color(0xD9000000),
            columnBottomColor = Color(0xE6000000),
            contentHorizontalAlignment = Alignment.CenterHorizontally,
            contentVerticalArrangement =  Arrangement.SpaceBetween,
        ) {
            SingInContent(signInGoogleViewModel, catalogViewModel, onNavigateToHome)
        }
    }
}
