package com.arcieri.wagner.mvvm_recipebook

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.arcieri.wagner.mvvm_recipebook.navigation.RecipeBookNavigation
import com.arcieri.wagner.mvvm_recipebook.ui.theme.MVVM_RecipeBookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)


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


        setContent {

            RecipeBookApp {




                RecipeBookNavigation()

            }
        }
    }


}

@Composable
fun RecipeBookApp(content: @Composable () -> Unit) {





    MVVM_RecipeBookTheme() {
        content()
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVM_RecipeBookTheme {
    }
}