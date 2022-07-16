package com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
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

    GradientColumn(
        modifier = Modifier
            .fillMaxSize(),
        columnHorizontalAlignment = Alignment.CenterHorizontally,
        columnVerticalArrangement = Arrangement.Bottom ,
        topSpacerTransition = true,
        topSpacerTransitionHeight = 100.dp,
        topTransitionTopColor = Color(0x00000000),
        topTransitionMiddleColor = Color(0x1A000000),
        topTransitionBottomColor = Color(0x4D000000),
        bottomSpacerTransition = false,
        columnTopColor = Color(0x4D000000),
        columnMiddleColor = Color(0x4D000000),
        columnBottomColor = Color(0xCC000000),
        contentHorizontalAlignment = Alignment.CenterHorizontally,
        contentVerticalArrangement =  Arrangement.SpaceBetween,
    ) {
        RecipeNameTimeRow(recipe, textColor = RB_White)
    }
}