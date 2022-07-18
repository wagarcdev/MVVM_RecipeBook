package com.arcieri.wagner.mvvm_recipebook.presentation.screens.detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.detail.components.detail_screen_content.BaseRecipesRow
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.detail.components.detail_screen_content.IngredientsTable
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.detail.components.detail_screen_content.RecipeMethods
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.RecipeImageAndInfo

@Composable
fun DetailScreenContent(recipe: Recipe ) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            ) {
                RecipeImageAndInfo(recipe)
            }
        }


        Spacer(modifier = Modifier.height(4.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            if (recipe.baseRecipes.isNotEmpty()) {
                item { BaseRecipesRow(recipe) }
            }

            item { IngredientsTable(recipe) }

            item { Divider(modifier = Modifier.height(10.dp), color = Color(0x00FFFFFF)) }

            item { RecipeMethods(recipe) }

            item { Divider(modifier = Modifier.height(100.dp), color = Color(0x00FFFFFF)) }


        }
    }


}