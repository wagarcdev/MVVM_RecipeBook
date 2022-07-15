package com.arcieri.wagner.mvvm_recipebook.ui.screen.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.arcieri.wagner.mvvm_recipebook.ui.screen.catalog.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.ui.screen.detail.components.DetailScreenContent
import com.arcieri.wagner.mvvm_recipebook.ui.screen.detail.components.EditRecipeFAB

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenDetail(
    catalogViewModel: CatalogViewModel,
) {

    val recipe = catalogViewModel.recipe


    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .background(Color(0xFFFFFFFF))
            .fillMaxSize(),
        content =  { DetailScreenContent(recipe) },
        floatingActionButton = { EditRecipeFAB(catalogViewModel, recipe) },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = false
    )
}


@Preview
@Composable
fun ScreenRecipePreview() {
 //   ScreenRecipe(recipe = RecipeData().loadRecipe(LocalContext.current))
}