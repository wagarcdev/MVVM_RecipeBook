package com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.content.body

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Orange
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Transparent
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.Shapes
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.RecipeImageAndInfo

@Composable
fun RecipeCatalogButton(
    recipe: Recipe,
    border: BorderStroke = BorderStroke(2.dp, RB_Orange),
    onClick: () -> Unit,
    horizontalGradientColorsList: List<Color> = listOf(RB_Transparent, RB_Transparent)
) {


    Card(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(150.dp)
            .clickable { onClick.invoke() },
        shape = Shapes.medium,
        border = border
    ) {
        RecipeImageAndInfo(recipe, gradientColorsList = horizontalGradientColorsList)
    }

    Spacer(
        modifier = Modifier
            .height(12.dp)
            .fillMaxWidth()
    )
}

