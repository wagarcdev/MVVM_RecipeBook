package com.arcieri.wagner.mvvm_recipebook.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arcieri.wagner.mvvm_recipebook.ui.screen.add_edit.ScreenAddEditRecipe
import com.arcieri.wagner.mvvm_recipebook.ui.screen.catalog.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.ui.screen.catalog.ScreenCatalog
import com.arcieri.wagner.mvvm_recipebook.ui.screen.detail.ScreenDetail
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.ScreenMain

@Composable
fun RecipeBookNavigation() {

    val catalogViewModel: CatalogViewModel = hiltViewModel()

    catalogViewModel.navHostController = rememberNavController()

    NavHost(
        navController = catalogViewModel.navHostController,
        startDestination = Screens.MainScreen.name
    ) {



        /** MainScreen */
        composable(Screens.MainScreen.name){
            ScreenMain(catalogViewModel = catalogViewModel)
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