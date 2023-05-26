package com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.navigation.Screens
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.SignInViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content.DrawerMainMenuContent
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content.MainMenuButtons
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Black_75
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_OrangeDarker
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Transparent
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.BackgroundImageRow
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.GradientColumn
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MainMenu(
    catalogViewModel: CatalogViewModel,
    signInGoogleViewModel: SignInViewModel,
    navHostController: NavHostController
) {

    val coroutineScope = rememberCoroutineScope()

    val currentUserId by signInGoogleViewModel.userId.collectAsState()

    /**  TODO  change effect to check user */
    LaunchedEffect(currentUserId) {
        if (currentUserId == null) {
            coroutineScope.launch {
                navHostController.navigate(route = Screens.AuthScreen.name)
            }
        }

    }

    BackgroundImageRow()


    val scaffoldState = rememberScaffoldState()

    Scaffold(
        backgroundColor = RB_Transparent,
        modifier = Modifier
            .systemBarsPadding()
            .navigationBarsPadding(),
        scaffoldState = scaffoldState,
        drawerContent = { DrawerMainMenuContent(signInGoogleViewModel) },
        drawerShape = RoundedCornerShape(topEnd = 100.dp),
        drawerContentColor = RB_Transparent,
        drawerBackgroundColor = RB_Transparent,
        drawerScrimColor = RB_Black_75,
        drawerElevation = 0.dp
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Card(
                shape = CircleShape,
                border = BorderStroke(3.dp, RB_OrangeDarker),
                modifier = Modifier
                    .padding(32.dp)
                    .size(75.dp)
                    .clickable {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }

                    },
                elevation = 3.dp
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(1f),
                    model =
                    if (signInGoogleViewModel.userProfileImage.value == "") {
                        R.drawable.blank_profile_picture
                    } else {
                        signInGoogleViewModel.userProfileImage.value
                    },
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    filterQuality = FilterQuality.Medium
                )
            }

        }

        GradientColumn(
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxSize(),
            columnHorizontalAlignment = Alignment.CenterHorizontally,
            columnVerticalArrangement = Arrangement.Bottom,
            topSpacerTransition = true,
            topSpacerTransitionHeight = 200.dp,
            topTransitionTopColor = Color(0x00000000),
            topTransitionMiddleColor = Color(0x33000000),
            topTransitionBottomColor = Color(0xCC000000),
            bottomSpacerTransition = false,
            columnTopColor = Color(0xCC000000),
            columnMiddleColor = Color(0xFA000000),
            columnBottomColor = Color(0xFA000000),
            contentHorizontalAlignment = Alignment.CenterHorizontally,
            contentVerticalArrangement = Arrangement.SpaceBetween,
        ) {
            MainMenuButtons(navHostController)
        }
    }
}

