package com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.contents.recipe_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Transparent
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.IngredientItem
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.IngredientsSectionTittleRow

@Composable
fun IngredientsTable(recipe: Recipe) {

    IngredientsSectionTittleRow()

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .wrapContentHeight()
            .fillMaxWidth(0.9f)
    ) {

        if (recipe.ingredients.isNotEmpty()) {

            recipe.ingredients.forEachIndexed { index, ingredient ->

                ingredient.adjustMeasuringUnits()

                IngredientItem(
                    ingredient,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    fontColor =
                    if ( index % 2 == 0 ) {
                        Color(0xFFFFFFFF)
                    } else {
                        Color(0xFFFFFFFF)
                    },
                    backgroundColor =
                    if ( index % 2 == 0 ) {
                        RB_Transparent
                    } else {
                        Color(0xFF525252)
                    }
                )
            }
        }//if
    }//Column
}//fun