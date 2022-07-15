package com.arcieri.wagner.mvvm_recipebook.ui.screen.add_edit.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.ui.screen.add_edit.components.edit_ingredients_section.AddNewItemButtonCard
import com.arcieri.wagner.mvvm_recipebook.ui.screen.add_edit.components.edit_ingredients_section.IngredientIsNotSelectedContent
import com.arcieri.wagner.mvvm_recipebook.ui.screen.add_edit.components.edit_ingredients_section.IngredientIsSelectedContent
import com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets.AnimatedCardView

@Composable
fun EditIngredientsSection(
    recipeDraft: Recipe,
    itemPadding: Dp
) {

    var isVisible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.padding(itemPadding))



        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .height(40.dp)
                .fillMaxSize()
                .clickable { isVisible = !isVisible },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Text(
                modifier = Modifier
                    .padding(horizontal = 14.dp),
                text = "Ingredients",
                color = Color(0xFF000000),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .rotate(
                        if (isVisible) {
                            0f
                        } else {
                            -90f
                        }
                    ),
                imageVector =
                if (isVisible) {
                    Icons.Default.KeyboardArrowDown
                } else {
                    Icons.Default.KeyboardArrowDown
                },
                contentDescription = "Arrow Icon"
            )


        }

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
                buttonText = "Ingredient",
                minHeight = 60.dp,
                onClick = {

                },
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
        AnimatedVisibility(
            visible = isVisible,
            enter = EnterTransition.None,
            exit = ExitTransition.None
        ) {
            Spacer(modifier = Modifier.padding(itemPadding))
        }
    }




}