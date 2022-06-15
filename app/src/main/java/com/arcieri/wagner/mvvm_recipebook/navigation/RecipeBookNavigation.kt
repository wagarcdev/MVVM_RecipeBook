package com.arcieri.wagner.mvvm_recipebook.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arcieri.wagner.mvvm_recipebook.screen.*

@Composable
fun RecipeBookNavigation(recipeBookViewModel: RecipeBookViewModel) {


    recipeBookViewModel.navHostController = rememberNavController()

    NavHost(
        navController = recipeBookViewModel.navHostController,
        startDestination = RecipeBookScreens.MainScreen.name
    ) {



        /** MainScreen */
        composable(RecipeBookScreens.MainScreen.name){
            ScreenMain(recipeBookViewModel = recipeBookViewModel)
        }

        /** Catalog*/
        composable(RecipeBookScreens.CatalogScreen.name){
            ScreenCatalog(recipeBookViewModel = recipeBookViewModel)
        }

        /** RecipeScreen */
        composable(
            RecipeBookScreens.RecipeScreen.name+"/{recipe}",
            arguments = listOf(navArgument("recipe") {type = NavType.StringType})
        ) {
            navBackStackEntry ->

            ScreenRecipe(
                recipeBookViewModel = recipeBookViewModel,
                recipeName = navBackStackEntry.arguments?.getString("recipe")
            )
        }

        /** AddEditRecipeScreen */
        composable(
            RecipeBookScreens.AddEditRecipeScreen.name+"/{recipe}",
            arguments = listOf(navArgument("recipe") {type = NavType.StringType})
        ) {
            navBackStackEntry ->

            ScreenAddEditRecipe(
                recipeBookViewModel = recipeBookViewModel,
                recipeName = navBackStackEntry.arguments?.getString("recipe")
            )
        }

    }


}