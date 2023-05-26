package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.model.Recipe

@Composable
fun RecipeTimeColumn(
    recipe: Recipe,
    colors: Color,
    fontSize: TextUnit = 10.sp,
    iconSize: Dp = 15.dp
) {

    var recipeMinutesLeft = recipe.recipeTimeInMinutes
    var recipeHours = 0

    if (recipe.recipeTimeInMinutes >= 60) {
        recipeHours = recipe.recipeTimeInMinutes / 60
        recipeMinutesLeft %= 60
    }

    Column(
        modifier = Modifier
            .wrapContentWidth()
    ) {
        Row(modifier = Modifier
            .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier
                    .size(iconSize),
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = "Tempo de Preparo ",
                tint = colors
            )



            val recipeTimeNullString = " add time"
            Text(
                fontSize = fontSize,
                fontWeight = FontWeight.ExtraBold,
                color = colors,
                text =
                if (recipe.recipeTimeInMinutes == 0) {
                    recipeTimeNullString
                } else {
                    if (recipeHours == 0) {
                        " ${recipeMinutesLeft}min"
                    } else {
                        if (recipeMinutesLeft == 0) {
                            " ${recipeHours}h"
                        } else {
                            " ${recipeHours}h${recipeMinutesLeft}min"
                        }

                    }
                }

            )
        }
    }




}