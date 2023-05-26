package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Transparent
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_White

@Composable
fun RecipeImageAndInfo(
    recipe: Recipe,
    titleFontSize: TextUnit = 22.sp,
    infoFontSize: TextUnit = 14.sp,
    infoIconsSize: Dp = 10.dp,
    showTimeAndPortions: Boolean = true,
    horizontalGradient: Boolean = true,
    gradientColorsList: List<Color> = listOf(RB_Transparent, RB_Transparent)
) {

    Row(
        modifier = Modifier
            .fillMaxSize(1f)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(1f),
            model =
            if (recipe.imageFilepath == "" || recipe.imageFilepath == null) {
                R.drawable.no_image
            } else {
                recipe.imageFilepath
            },
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.Medium
        )
    }

    Column() {
        Row(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(
                    Brush.horizontalGradient( gradientColorsList )
                )

        ) {
            GradientColumn(
                modifier = Modifier
                    .fillMaxSize(),
                columnHorizontalAlignment = Alignment.CenterHorizontally,
                columnVerticalArrangement = Arrangement.Bottom ,
                topSpacerTransition = true,
                topSpacerTransitionHeight = 20.dp,
                topTransitionTopColor = Color(0x00000000),
                topTransitionMiddleColor = Color(0x1A000000),
                topTransitionBottomColor = Color(0x4D000000),
                bottomSpacerTransition = false,
                columnTopColor = Color(0x4D000000),
                columnMiddleColor = Color(0x99000000),
                columnBottomColor = Color(0xE6000000),
                contentHorizontalAlignment = Alignment.CenterHorizontally,
                contentVerticalArrangement =  Arrangement.SpaceBetween,
            ) {
                RecipeInfoColumnRows(
                    recipe,
                    titleFontColor = RB_White,
                    titleFontSize = titleFontSize,
                    showTimeAndPortions = showTimeAndPortions,
                    infoFontSize = infoFontSize,
                    infoIconsSize = infoIconsSize
                )
            }
        }
    }


}