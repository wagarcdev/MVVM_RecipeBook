package com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.data.RecipeData
import com.arcieri.wagner.mvvm_recipebook.model.Recipe

@Composable
fun RecipeTimeColumn(
    recipe: Recipe,
    colors: Color,
    fontSize: TextUnit = 10.sp,
    iconSize: Dp = 15.dp
) {

    var recipeMinutes = recipe.recipeTime
    var recipeHours = 0

    if (recipeMinutes >= 60) {
        recipeHours = recipeMinutes / 60
        recipeMinutes %= 60
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
                if (recipe.recipeTime == 0) {
                    recipeTimeNullString
                } else {
                    if (recipeHours == 0) {
                        " ${recipeMinutes}min"
                    } else {
                        if (recipeMinutes == 0) {
                            " ${recipeHours}h"
                        } else {
                            " ${recipeHours}h${recipeMinutes}min"
                        }

                    }
                }

            )
        }
    }




}

@Preview(showBackground = true)
@Composable
fun RecipeTimePreview(){

    val recipe = RecipeData().loadRecipe(LocalContext.current)

    RecipeTimeColumn(recipe, Color.LightGray)
}