package com.arcieri.wagner.mvvm_recipebook.navigation

enum class RecipeBookScreens {

    MainScreen,
    RecipeScreen,
    CatalogScreen,
    AddEditRecipeScreen;

    companion object {
        fun fromRoute(route: String?): RecipeBookScreens
        = when (route?.substringBefore("/")) {

            MainScreen.name -> MainScreen
            RecipeScreen.name -> RecipeScreen
            CatalogScreen.name -> CatalogScreen
            AddEditRecipeScreen.name -> AddEditRecipeScreen
            null -> MainScreen
            else -> throw IllegalAccessException("Route $route is not recognized")

        }
    }

}

