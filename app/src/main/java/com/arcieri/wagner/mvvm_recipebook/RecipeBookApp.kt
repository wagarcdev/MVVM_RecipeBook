package com.arcieri.wagner.mvvm_recipebook

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.arcieri.wagner.mvvm_recipebook.navigation.RecipeBookNavigation
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.MVVM_RecipeBookTheme

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun RecipeBookApp() {

    MVVM_RecipeBookTheme() {
        RecipeBookNavigation()
    }
}