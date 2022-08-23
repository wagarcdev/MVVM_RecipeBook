package com.arcieri.wagner.mvvm_recipebook.navigation

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.ScreenAddEditRecipe
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.AuthScreen
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.SignInGoogleViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.auth.sign_in.SignInGoogleViewModelFactory
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.catalog.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.catalog.ScreenCatalog
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.ScreenDetails
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.MainMenu

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun RecipeBookNavigation() {

    val context = LocalContext.current

    val catalogViewModel: CatalogViewModel = hiltViewModel()
    val signInGoogleViewModel: SignInGoogleViewModel = viewModel(
        factory = SignInGoogleViewModelFactory(context.applicationContext as Application)
    )

    catalogViewModel.navHostController = rememberNavController()

    val isUserSigned =  signInGoogleViewModel.checkSignedInUser(context)



    NavHost(
        startDestination =
        if (isUserSigned) {
            Screens.MainMenuScreen.name
        } else {
            Screens.AuthScreen.name
        },
        navController = catalogViewModel.navHostController
    ) {



        /** AuthScreen */
        composable(Screens.AuthScreen.name){
            AuthScreen(catalogViewModel = catalogViewModel, signInGoogleViewModel)
        }

        /** MainMenuScreen */
        composable(Screens.MainMenuScreen.name){
            MainMenu(catalogViewModel = catalogViewModel, signInGoogleViewModel)
        }


        /** Catalog*/
        composable(Screens.CatalogScreen.name){
            ScreenCatalog(catalogViewModel = catalogViewModel)
        }

        composable(Screens.DetailScreen.name) {
            ScreenDetails(
                catalogViewModel = catalogViewModel,
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