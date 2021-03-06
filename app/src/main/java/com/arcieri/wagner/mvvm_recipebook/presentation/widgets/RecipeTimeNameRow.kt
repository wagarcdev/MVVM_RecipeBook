package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.data.RecipeData
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Black

@Composable
fun RecipeInfoColumnRows(
    recipe: Recipe,
    textColor: Color = RB_Black,
    iconsColor: Color = Color.Gray
) {
    Column(
        modifier = Modifier
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

            if (recipe.portions > 0 || recipe.recipeTime > 0) {
                TimeAndPortionsColumn(recipe)
            }
        }

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
                color = textColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Start,
                lineHeight = 15.sp
            )
        }
    }



}

@Preview(showBackground = true)
@Composable
fun RecipeTimeNameRowPreview() {

    val recipe = RecipeData().loadRecipe(LocalContext.current)

    RecipeInfoColumnRows(recipe)
}