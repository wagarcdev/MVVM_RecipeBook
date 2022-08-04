package com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.contents.recipe_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.contents.recipe_details.components.IngredientsTable

@Composable
fun RecipeDetailsContent(recipe: Recipe) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

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