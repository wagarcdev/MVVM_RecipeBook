package com.arcieri.wagner.mvvm_recipebook.navigation

enum class Screens {

    AuthScreen,
    MainMenuScreen,
    DetailScreen,
    MainScreen,
    TestScreen,
    AddEditRecipeScreen;

    companion object {
        fun fromRoute(route: String?): Screens
        = when (route?.substringBefore("/")) {

            AuthScreen.name -> AuthScreen
            MainMenuScreen.name -> MainMenuScreen
            DetailScreen.name -> DetailScreen
            MainScreen.name -> MainScreen
            AddEditRecipeScreen.name -> AddEditRecipeScreen
            TestScreen.name -> TestScreen
            null -> AuthScreen
            else -> throw IllegalAccessException("Route $route is not recognized")

        }
    }

}

