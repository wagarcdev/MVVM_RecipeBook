package com.arcieri.wagner.mvvm_recipebook.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe

@Composable
fun TimePortionsRow(recipe: Recipe) {

    Row(
        modifier = Modifier
            .height(25.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (recipe.portions != null) {
            ShowPortionsTextRow(recipe)
        }

        Divider(modifier = Modifier.width(2.dp))

        RecipeTime(recipe)

    }//Row


}//fun