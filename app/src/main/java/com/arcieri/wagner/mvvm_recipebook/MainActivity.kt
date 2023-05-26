package com.arcieri.wagner.mvvm_recipebook

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.arcieri.wagner.mvvm_recipebook.navigation.RecipeBookNavigation
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.MVVM_RecipeBookTheme
import dagger.hilt.android.AndroidEntryPoint

/**  TODO
 *
 *   - Tabela Nutricional
 *   - CMV
 *   - Proporções
 *   - Calculadora de Lucro
 *
 *   Google Cloud Platform - Android ClientID
 *   1025710702287-92h753vaqpgtp6f3ic88m1lsko48p8qc.apps.googleusercontent.com
 *
 * **/

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Transparency for StatusBar
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent { RecipeBookApp() }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun RecipeBookApp() {

    MVVM_RecipeBookTheme() {
        RecipeBookNavigation()
    }
}