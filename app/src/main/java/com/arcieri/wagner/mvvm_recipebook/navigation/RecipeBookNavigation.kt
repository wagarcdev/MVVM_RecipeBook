package com.arcieri.wagner.mvvm_recipebook.navigation

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arcieri.wagner.mvvm_recipebook.ui.screen.add_edit.ScreenAddEditRecipe
import com.arcieri.wagner.mvvm_recipebook.ui.screen.catalog.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.ui.screen.catalog.ScreenCatalog
import com.arcieri.wagner.mvvm_recipebook.ui.screen.detail.ScreenDetail
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.ScreenMain
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth.SignInGoogleViewModel
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth.SignInGoogleViewModelFactory

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun RecipeBookNavigation() {

    val context = LocalContext.current

    val catalogViewModel: CatalogViewModel = hiltViewModel()
    val signInGoogleViewModel: SignInGoogleViewModel = viewModel(
        factory = SignInGoogleViewModelFactory(context.applicationContext as Application)
    )

    catalogViewModel.navHostController = rememberNavController()

    NavHost(
        navController = catalogViewModel.navHostController,
        startDestination = Screens.MainScreen.name
    ) {



        /** MainScreen */
        composable(Screens.MainScreen.name){
            ScreenMain(catalogViewModel = catalogViewModel, signInGoogleViewModel)
        }

        /** Catalog*/
        composable(Screens.CatalogScreen.name){
            ScreenCatalog(catalogViewModel = catalogViewModel)
        }

        composable(Screens.DetailScreen.name) {
            ScreenDetail(
                catalogViewModel = catalogViewModel,
            )
        }

        /** AddEditRecipeScreen */
        composable(Screens.AddEditRecipeScreen.name) {

            ScreenAddEditRecipe(
                catalogViewModel = catalogViewModel,
            )

        }

    }


}