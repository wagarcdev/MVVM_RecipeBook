package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_ingredients_section.AddNewItemButtonCard
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_ingredients_section.IngredientIsNotSelectedContent
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_ingredients_section.IngredientIsSelectedContent
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.AnimatedCardView
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.IngredientsSectionTittleRow

@Composable
fun EditIngredientsSection(
    recipeDraft: Recipe,
    itemPadding: Dp
) {



    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.padding(itemPadding))

        var isVisible by remember { mutableStateOf(true) }

        IngredientsSectionTittleRow()

        recipeDraft.ingredients.forEach { ingredient ->
            ingredient.adjustMeasuringUnits()

            AnimatedCardView(
                isNotSelectedContent = { IngredientIsNotSelectedContent(ingredient) },
                isSelectedContent = {
                    IngredientIsSelectedContent(
                        ingredient,
                        onTextQuantityChange = {})
                },
                isSelectedBorderColor = Color(0xFF0022A3),
                fillMaxWidthFloat = 0.9f
            )

            Spacer(modifier = Modifier.padding(itemPadding))

        }

        Spacer(modifier = Modifier.padding(itemPadding))

        AddNewItemButtonCard(
            buttonText = "ADD Ingredient",
            minHeight = 60.dp,
            onClick = {

            },
            fillMaxWidthFloat = 0.75f
        )

        Spacer(modifier = Modifier.padding(itemPadding))

        Spacer(modifier = Modifier.padding(itemPadding))
    }




}

