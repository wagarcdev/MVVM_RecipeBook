package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_ingredients_section

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.model.Ingredient
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.IngredientItem

/**
 *
 *
 *   AnimatedCardView Contents
 *
 * */
@Composable
fun IngredientIsNotSelectedContent(ingredient: Ingredient) {
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {
        Row(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IngredientItem(
                ingredient = ingredient,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }
}