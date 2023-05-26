package com.arcieri.wagner.mvvm_recipebook.presentation.screens.main

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.SignInViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.content.MainScrContentBody
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.content.MainScrContentHeader
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.content.fab.MainScreenFAB
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content.DrawerMainMenuContent
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.*
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.BackgroundImageRow
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.GradientColumn

@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    catalogViewModel: CatalogViewModel,
    signInGoogleViewModel: SignInViewModel,
    navHostController: NavHostController
) {

    val recipeDatabase = catalogViewModel.recipeList.collectAsState().value

    val scaffoldState = rememberScaffoldState()


    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = { DrawerMainMenuContent(signInGoogleViewModel) },
        drawerShape = RoundedCornerShape(topEnd = 100.dp),
        drawerContentColor = RB_Transparent,
        drawerBackgroundColor = RB_Transparent,
        drawerScrimColor = RB_Black_85,
        drawerElevation = 0.dp,
        floatingActionButton = { MainScreenFAB() },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false,
        content = {
            MainScreenContent(
                scaffoldState,
                signInGoogleViewModel,
                recipeDatabase,
                catalogViewModel,
                navHostController
            )
        }
    )
}


@RequiresApi(Build.VERSION_CODES.R)
@Composable
private fun MainScreenContent(
    scaffoldState: ScaffoldState,
    signInGoogleViewModel: SignInViewModel,
    recipeDatabase: List<Recipe>,
    catalogViewModel: CatalogViewModel,
    navHostController: NavHostController
) {

    val primaryFontColor = RB_White
    val secondaryFontColor = RB_Orange
    val secondaryVariantFontColor = RB_OrangeLight
    val buttonsBorderColorNotFocused = RB_Orange_50

    Row() {
        Box(){
            BackgroundImageRow()
            Box(modifier = Modifier
                .matchParentSize()
                .background(RB_Black_75))
        }
    }

    GradientColumn(
        topSpacerTransition = false,
        bottomSpacerTransition = false,
        columnTopColor = RB_Black_50,
        columnMiddleColor = RB_Black_75,
        columnBottomColor = RB_Black_85
    ) {
        Column(
            modifier = Modifier
                .systemBarsPadding()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            MainScrContentHeader(scaffoldState, signInGoogleViewModel, secondaryVariantFontColor)

            MainScrContentBody(
                secondaryFontColor,
                recipeDatabase,
                buttonsBorderColorNotFocused,
                catalogViewModel,
                navHostController
            )

            TextField(value = "null", onValueChange = {})
        }
    }




}






