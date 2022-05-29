package com.arcieri.wagner.mvvm_recipebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.arcieri.wagner.mvvm_recipebook.data.RecipeData
import com.arcieri.wagner.mvvm_recipebook.screen.ScreenAddEditRecipe
import com.arcieri.wagner.mvvm_recipebook.ui.theme.MVVM_RecipeBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVM_RecipeBookTheme {

                RecipeBookApp()

//                ScreenRecipe(recipe = RecipeData().loadRecipe(LocalContext.current))

            }
        }
    }
}
@Composable
fun RecipeBookApp() {

//    val recipeList = viewModel<RecipeViewModel>().recipeList.collectAsState().value

    ScreenAddEditRecipe( recipe = RecipeData().loadRecipe(LocalContext.current) )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MVVM_RecipeBookTheme {
    }
}