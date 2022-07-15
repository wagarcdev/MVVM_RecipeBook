package com.arcieri.wagner.mvvm_recipebook.navigation

enum class Screens {

    MainScreen,
    DetailScreen,
    CatalogScreen,
    AddEditRecipeScreen;

    companion object {
        fun fromRoute(route: String?): Screens
        = when (route?.substringBefore("/")) {

            MainScreen.name -> MainScreen
            DetailScreen.name -> DetailScreen
            CatalogScreen.name -> CatalogScreen
            AddEditRecipeScreen.name -> AddEditRecipeScreen
            null -> MainScreen
            else -> throw IllegalAccessException("Route $route is not recognized")

        }
    }

}

