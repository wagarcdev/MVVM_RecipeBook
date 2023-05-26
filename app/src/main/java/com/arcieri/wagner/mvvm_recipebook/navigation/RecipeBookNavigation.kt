package com.arcieri.wagner.mvvm_recipebook.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.ScreenAddEditRecipe
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.AuthScreen
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.SignInViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.ScreenDetails
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.MainScreen
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.MainMenu

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun RecipeBookNavigation(
    catalogViewModel: CatalogViewModel = hiltViewModel(),
    signInGoogleViewModel: SignInViewModel = hiltViewModel()
) {

    val navHostController = rememberNavController()

    val currentUserId: String? by signInGoogleViewModel.userId.collectAsState()

    NavHost(
        startDestination =
        if (currentUserId != null) {
            Screens.MainScreen.name
        } else {
            Screens.AuthScreen.name
        },
        navController = navHostController
    ) {

        /** AuthScreen */
        composable(Screens.AuthScreen.name){
            AuthScreen(
                catalogViewModel = catalogViewModel,
                signInGoogleViewModel = signInGoogleViewModel,
                onNavigateToHome = { navHostController.navigate(Screens.MainMenuScreen.name) }
            )
        }

        /** MainMenuScreen */
        composable(Screens.MainMenuScreen.name){
            MainMenu(
                catalogViewModel = catalogViewModel,
                signInGoogleViewModel = signInGoogleViewModel,
                navHostController = navHostController
            )
        }


        /** Catalog*/
        composable(Screens.MainScreen.name){
            MainScreen(
                catalogViewModel = catalogViewModel,
                signInGoogleViewModel = signInGoogleViewModel,
                navHostController = navHostController
            )
        }

        composable(Screens.DetailScreen.name) {
            ScreenDetails(
                catalogViewModel = catalogViewModel,
                navHostController = navHostController
            )
        }

        /** AddEditRecipeScreen */
        composable(
            route = Screens.AddEditRecipeScreen.name + "/{recipeId}",
            arguments = listOf(navArgument("recipeId") {
                type = NavType.LongType
                defaultValue = -1
            })
        ) {

            val recipeId = remember {
                it.arguments?.getLong("recipeId")
            }

            if (recipeId != null) {
                ScreenAddEditRecipe(
                    catalogViewModel = catalogViewModel,
                    recipeId = recipeId
                )
            }

        }

    }


}