package com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.contents.recipe_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.catalog.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.contents.recipe_details.components.IngredientsTable

@Composable
fun RecipeDetailsContent(catalogViewModel: CatalogViewModel) {


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Spacer(modifier = Modifier.height(4.dp))

        catalogViewModel.currentRecipe?.let {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {

                if (catalogViewModel.currentRecipe?.baseRecipes?.isNotEmpty() == true) {
                    item { BaseRecipesRow(catalogViewModel.currentRecipe!!) }
                }

                item { IngredientsTable(catalogViewModel.currentRecipe!!) }

                item { Divider(modifier = Modifier.height(10.dp), color = Color(0x00FFFFFF)) }

                item { RecipeMethods(catalogViewModel.currentRecipe!!) }

                item { Divider(modifier = Modifier.height(100.dp), color = Color(0x00FFFFFF)) }


            }
        }

    }


}