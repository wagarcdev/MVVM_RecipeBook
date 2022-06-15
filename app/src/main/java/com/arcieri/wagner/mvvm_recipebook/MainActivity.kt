package com.arcieri.wagner.mvvm_recipebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arcieri.wagner.mvvm_recipebook.navigation.RecipeBookNavigation
import com.arcieri.wagner.mvvm_recipebook.screen.RecipeBookViewModel
import com.arcieri.wagner.mvvm_recipebook.ui.theme.MVVM_RecipeBookTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVM_RecipeBookTheme {

               RecipeBookApp {
                   val recipeBookViewModel: RecipeBookViewModel = viewModel()
                   RecipeBookNavigation(recipeBookViewModel)
               }

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