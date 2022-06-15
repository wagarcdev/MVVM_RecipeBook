package com.arcieri.wagner.mvvm_recipebook.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.data.RecipeData
import com.arcieri.wagner.mvvm_recipebook.model.Recipe

@Composable
fun RecipeTime(recipe: Recipe) {
    var recipeMinutes = recipe.recipeTime
    var recipeHours = 0

    if (recipeMinutes != null) {
        if (recipeMinutes >= 60) {
            recipeHours = recipeMinutes / 60
            recipeMinutes %= 60
        }
    }

    Row(modifier = Modifier
        .height(25.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier
                .height(20.dp),
            painter = painterResource(id = R.drawable.ic_clock),
            contentDescription = "Tempo de Preparo ",
            tint = Color(0xFF7A7A7A)
        )



        val recipeTimeNullString = " add time"
        Text(
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF7A7A7A),
            text =
            if (recipe.recipeTime == null || recipe.recipeTime == 0) {
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

@Preview(showBackground = true)
@Composable
fun RecipeTimePreview(){

    val recipe = RecipeData().loadRecipe(LocalContext.current)

    RecipeTime(recipe)
}