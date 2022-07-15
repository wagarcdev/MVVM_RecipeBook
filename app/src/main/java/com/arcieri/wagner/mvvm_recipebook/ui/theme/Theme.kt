package com.arcieri.wagner.mvvm_recipebook.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = RB_Orange,
    onPrimary = RB_OrangeLightExtra,
    primaryVariant = RB_OrangeDarkDeep,
    secondary = RB_Green,
    onSecondary = RB_GreenLightExtra,
    secondaryVariant = RB_GreenDarkDeep,
    surface = RB_OrangeLightExtra
)

private val LightColorPalette = lightColors(
    primary = RB_OrangeDark,
    onPrimary = RB_OrangeLight,
    primaryVariant = RB_OrangeDarkNeutral,
    secondary = RB_GreenLight,
    onSecondary = RB_GreenDarkExtra,
    secondaryVariant = RB_GreenDarkNeutral,
    surface = RB_OrangeLightExtra



    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MVVM_RecipeBookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}