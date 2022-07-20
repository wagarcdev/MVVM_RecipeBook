package com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.RecipeImageAndInfo

@Composable
fun DetailScreenTopBar(recipe: Recipe) {
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
}