package com.arcieri.wagner.mvvm_recipebook.ui.screen.detail.components.detail_screen_content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets.IngredientItem

@Composable
fun IngredientsTable(recipe: Recipe) {

    var isIngredientsVisible by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(40.dp)
            .fillMaxSize()
            .clickable { isIngredientsVisible = !isIngredientsVisible },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            modifier = Modifier
                .size(30.dp)
                .rotate(
                    if (isIngredientsVisible) {
                        0f
                    } else {
                        -90f
                    }
                ),
            imageVector =
            if (isIngredientsVisible) {
                Icons.Default.KeyboardArrowDown
            } else {
                Icons.Default.KeyboardArrowDown
            },
            contentDescription = "Arrow Icon"
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 14.dp),
            text = "Ingredientes",
            color = Color(0xFF000000),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .wrapContentHeight()
            .fillMaxWidth(0.9f)
    ) {

        if (recipe.ingredients.isNotEmpty()) {
            for (ingredient in recipe.ingredients) {

                ingredient.adjustMeasuringUnits()

                AnimatedVisibility(visible = isIngredientsVisible) {
                    IngredientItem(
                        ingredient,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }

                AnimatedVisibility(visible = isIngredientsVisible) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(start = 25.dp)
                            .height(1.dp)
                            .background(Color(0xFFE6E6E6))
                    )
                }

                AnimatedVisibility(visible = isIngredientsVisible) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = (Color(0x00000000))
                    )
                }

            }//for ingredient
        }//if


    }//Column
}//fun