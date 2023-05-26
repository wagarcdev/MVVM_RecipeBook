package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe

@Composable
fun TimeAndPortionsColumn(
    recipe: Recipe,
    infoFontSize: TextUnit = 10.sp,
    infoIconsSize: Dp = 15.dp,
    infoIconsColor: Color = Color.LightGray
) {


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



            if (recipe.recipeTimeInMinutes > 0) {

                RecipeTimeColumn(recipe, infoIconsColor, infoFontSize, infoIconsSize)
            }

            if (recipe.portions > 0 && recipe.recipeTimeInMinutes > 0) {

                Spacer(modifier = Modifier.width(8.dp))
            }



            if (recipe.portions > 0) {

                ShowPortionsTextColumn(recipe, infoIconsColor, infoFontSize, infoIconsSize)
            }





        }



    }//Row


}//fun
