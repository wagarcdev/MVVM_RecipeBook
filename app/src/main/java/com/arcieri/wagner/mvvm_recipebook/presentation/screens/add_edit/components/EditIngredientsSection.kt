package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
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

        isVisible = IngredientsSectionTittleRow(isVisible)

        recipeDraft.ingredients.forEach { ingredient ->
            ingredient.adjustMeasuringUnits()

            AnimatedVisibility(
                visible = isVisible,
                enter = EnterTransition.None,
                exit = ExitTransition.None
            ) {
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
            }
            AnimatedVisibility(
                visible = isVisible,
                enter = EnterTransition.None,
                exit = ExitTransition.None
            ) {
                Spacer(modifier = Modifier.padding(itemPadding))
            }
        }


        AnimatedVisibility(
            visible = isVisible,
            enter = EnterTransition.None,
            exit = ExitTransition.None
        ) {
            Spacer(modifier = Modifier.padding(itemPadding))
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = EnterTransition.None,
            exit = ExitTransition.None
        ) {


            AddNewItemButtonCard(
                buttonText = "ADD Ingredient",
                minHeight = 60.dp,
                onClick = {

                },
                fillMaxWidthFloat = 0.75f
            )


        }
        AnimatedVisibility(
            visible = isVisible,
            enter = EnterTransition.None,
            exit = ExitTransition.None
        ) {
            Spacer(modifier = Modifier.padding(itemPadding))
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = EnterTransition.None,
            exit = ExitTransition.None
        ) {
            Spacer(modifier = Modifier.padding(itemPadding))
        }
    }




}

