package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Black

@Composable
fun RecipeInfoColumnRows(
    recipe: Recipe,
    titleFontColor: Color = RB_Black,
    titleFontSize: TextUnit = 22.sp,
    infoFontSize: TextUnit = 14.sp,
    infoIconsSize: Dp = 10.dp,
    iconsColor: Color = Color.Gray,
    showTimeAndPortions: Boolean = true
) {
    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) {

        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(start = 20.dp, bottom = 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {

            Text(
                modifier = Modifier
                    .weight(1f),
                text = recipe.name,
                color = titleFontColor,
                fontSize = titleFontSize,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Start,
                lineHeight = 15.sp
            )
        }

        if(showTimeAndPortions) {
            Row(
                modifier = Modifier
                    .defaultMinSize(minHeight = 20.dp)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(start = 20.dp, bottom = 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {

                if (recipe.portions > 0 || recipe.recipeTimeInMinutes > 0) {
                    TimeAndPortionsColumn(
                        recipe = recipe,
                        infoFontSize = infoFontSize,
                        infoIconsSize = infoIconsSize,
                        infoIconsColor = iconsColor
                    )
                }
            }
        }


    }



}
