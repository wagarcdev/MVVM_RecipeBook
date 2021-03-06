package com.arcieri.wagner.mvvm_recipebook.presentation.screens.catalog.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.RecipeImageAndInfo
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_OrangeDark
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.Shapes

@Composable
fun RecipeCatalogButton(
    recipe: Recipe,
    onClick: () -> Unit
) {


    Card(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(160.dp)
            .clickable { onClick.invoke() },
        shape = Shapes.medium,
        border = BorderStroke(2.dp, RB_OrangeDark)
    ) {
        RecipeImageAndInfo(recipe)
    }

    Spacer(
        modifier = Modifier
            .height(12.dp)
            .fillMaxWidth()
    )
}

