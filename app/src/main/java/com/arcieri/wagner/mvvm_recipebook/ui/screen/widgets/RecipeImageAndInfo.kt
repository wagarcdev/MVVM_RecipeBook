package com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.ui.theme.RB_White

@Composable
fun RecipeImageAndInfo(recipe: Recipe) {

    val noImage = R.drawable.no_image
    val recipeImage = remember { mutableStateOf(recipe.imageFilepath) }


    Row(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(1f),
            model =
            if (recipeImage.value == "") {
                noImage
            } else {
                recipeImage.value
            },
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.Medium
        )
    }

    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize(1f)

    ) {
        Row(
            modifier = Modifier
                .weight(4f)
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0x00000000),
                            Color(0x1A000000)
                        ),
                    )
                )
        ) { }

        // GRADIENT TRANSITION ROW
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0x1A000000),
                            Color(0x4D000000)
                        ),
                    )
                )
        ) { }


        /** RECIPE NAME AND INFO */
        Row(
            modifier = Modifier
                .weight(2f)
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0x4D000000),
                            Color(0xCC000000)
                        ),
                    )
                ),
            verticalAlignment = Alignment.Bottom
        ) {
            RecipeNameTimeRow(recipe, textColor = RB_White)
        }
    }
}