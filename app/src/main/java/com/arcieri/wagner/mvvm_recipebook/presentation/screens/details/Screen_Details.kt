package com.arcieri.wagner.mvvm_recipebook.presentation.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.DetailScreenContent
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.DetailScreenTopBar
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.contents.recipe_details.components.EditRecipeFAB
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.CatalogViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenDetails(
    catalogViewModel: CatalogViewModel,
    navHostController: NavHostController
) {


    catalogViewModel.currentRecipe?.let { recipe ->
        Scaffold(
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxSize(),
            topBar = { DetailScreenTopBar(recipe) },
            content =  { DetailScreenContent(catalogViewModel) },
            floatingActionButton = { recipe.id?.let {
                EditRecipeFAB(it, catalogViewModel, navHostController) } },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = false,
            backgroundColor = Color(0x00000000),
            contentColor = Color(0x00000000)
        )
    }



}


@Preview
@Composable
fun ScreenRecipePreview() {
 //   ScreenRecipe(recipe = RecipeData().loadRecipe(LocalContext.current))
}