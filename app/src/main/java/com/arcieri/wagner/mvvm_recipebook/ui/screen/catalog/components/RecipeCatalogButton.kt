package com.arcieri.wagner.mvvm_recipebook.ui.screen.catalog.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets.RecipeImageAndInfo
import com.arcieri.wagner.mvvm_recipebook.ui.theme.RB_White

@Composable
fun RecipeCatalogButton(
    recipe: Recipe,
    onClick: () -> Unit
) {

    val cornerDp = 30.dp

    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(200.dp)
            .clickable { onClick.invoke() },
        shape = RoundedCornerShape(cornerDp),
        border = BorderStroke(1.dp, RB_White)
    ) {
        RecipeImageAndInfo(recipe)
    }

    Spacer(
        modifier = Modifier
            .height(8.dp)
            .fillMaxWidth()
    )
}
