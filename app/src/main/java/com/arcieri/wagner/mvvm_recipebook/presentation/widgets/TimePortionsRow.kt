package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.data.local.RecipeData
import com.arcieri.wagner.mvvm_recipebook.model.Recipe

@Composable
fun TimeAndPortionsColumn(recipe: Recipe) {

    val colors = Color.LightGray
    val fontSize = 10.sp
    val iconSize = 15.dp

    Column(
        modifier = Modifier
            .height(20.dp)
            .wrapContentWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .width(180.dp),
            horizontalArrangement = Arrangement.Start
        ) {



            if (recipe.recipeTime > 0) {

                RecipeTimeColumn(recipe, colors, fontSize, iconSize)
            }

            if (recipe.portions > 0 && recipe.recipeTime > 0) {

                Spacer(modifier = Modifier.width(8.dp))
            }



            if (recipe.portions > 0) {

                ShowPortionsTextColumn(recipe, colors, fontSize, iconSize)
            }





        }



    }//Row


}//fun

@Preview
@Composable
fun TimePortionsRowPreview() {



    val recipe = RecipeData().loadRecipe(LocalContext.current)

    TimeAndPortionsColumn(recipe)


}