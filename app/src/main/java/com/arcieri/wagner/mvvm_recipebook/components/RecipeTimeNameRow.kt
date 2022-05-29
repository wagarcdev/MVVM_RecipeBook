package com.arcieri.wagner.mvvm_recipebook.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun RecipeTimeNameRow(recipe: Recipe) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        TimePortionsRow(recipe)


        Text(

            text = recipe.name!!,
            color = Color(0xFF000000),
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.End
        )

    }
}

@Preview(showBackground = true)
@Composable
fun RecipeTimeNameRowPreview() {

    val recipe = RecipeData().loadRecipe(LocalContext.current)

    RecipeTimeNameRow(recipe)
}