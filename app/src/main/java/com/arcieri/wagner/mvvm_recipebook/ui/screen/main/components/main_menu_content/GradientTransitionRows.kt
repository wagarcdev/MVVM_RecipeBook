package com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.main_menu_content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GradientTransitionRows(
    transitionHeight: Dp
) {

    val rowHeight = transitionHeight / 2

    /** Gradient Shadow Transition Row */
    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .height(rowHeight)
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0x00000000),
                        Color(0x33000000)
                    ),
                )
            ),
    ) { }


    /** Gradient Shadow Transition Row */
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(rowHeight)
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0x33000000),
                        Color(0xCC000000)
                    ),
                )
            )
    ) { }
}