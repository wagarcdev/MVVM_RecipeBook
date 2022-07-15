package com.arcieri.wagner.mvvm_recipebook.ui.screen.detail.components.detail_screen_content

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.model.Recipe

@Composable
fun LoadRecipeImage(recipe: Recipe) {



    val recipeImage = remember { mutableStateOf(recipe.imageFilepath) }

    val noImage = R.drawable.no_image

    Row(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth(),
            elevation = 15.dp,
            border = BorderStroke(1.dp, Color(0xFFD5D5D5))
        ) {
            AsyncImage(
                model =
                if (recipeImage.value == "") { noImage }
                else { recipeImage.value },
                contentDescription = "image",
                contentScale = ContentScale.FillWidth,
                filterQuality = FilterQuality.Medium
            )
        }


    }//Row
}//fun