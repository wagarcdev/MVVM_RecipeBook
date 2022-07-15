package com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.data.RecipeData
import com.arcieri.wagner.mvvm_recipebook.model.Recipe

@Composable
fun PortionsAndTimeColumn(recipe: Recipe) {

    val colors = Color.LightGray

    Column(
        modifier = Modifier
            .height(25.dp)
            .wrapContentWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .width(180.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            if (recipe.portions > 0) {

                ShowPortionsTextColumn(recipe, colors)
            }

            if (recipe.portions > 0 || recipe.recipeTime > 0) {

                Spacer(modifier = Modifier.width(10.dp))
            }

            if (recipe.recipeTime > 0) {

                RecipeTimeColumn(recipe, colors)
            }





        }



    }//Row


}//fun

@Preview
@Composable
fun TimePortionsRowPreview() {



    val recipe = RecipeData().loadRecipe(LocalContext.current)

    PortionsAndTimeColumn(recipe)


}