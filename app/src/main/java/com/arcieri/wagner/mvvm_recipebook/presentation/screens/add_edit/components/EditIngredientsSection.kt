package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_ingredients_section.AddNewItemButtonCard
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_ingredients_section.IngredientIsNotSelectedContent
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_ingredients_section.IngredientIsSelectedContent
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Orange
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Orange_50
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Transparent
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.AnimatedCardView
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.IngredientsSectionTittleRow

@Composable
fun EditIngredientsSection(
    catalogViewModel: CatalogViewModel,
    itemPadding: Dp
) {



    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.padding(itemPadding))

        IngredientsSectionTittleRow()

        catalogViewModel.currentRecipe?.ingredients?.forEach { ingredient ->
            ingredient.adjustMeasuringUnits()

            AnimatedCardView(
                isNotSelectedContent = { IngredientIsNotSelectedContent(ingredient) },
                isSelectedContent = {
                    IngredientIsSelectedContent(
                        ingredient,
                        onTextQuantityChange = {})
                },
                backgroundColor = RB_Transparent,
                isSelectedBorderColor = RB_Orange,
                isNotSelectedBorderColor = RB_Orange_50,
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

