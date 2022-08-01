package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedCardView(
    modifier: Modifier = Modifier,
    fillMaxWidthFloat: Float = 1f,
    spaceByPadding: Dp = 6.dp,
    isSelectedBorderColor: Color = Color(0xFF0022A3),
    isNotSelectedBorderColor: Color = Color(0xFF696969),
    isNotSelectedContent: @Composable () -> Unit,
    isSelectedContent: @Composable () -> Unit
) {
    var isSelected by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .padding(top = spaceByPadding)
            .clickable { isSelected = !isSelected }
    ) {
        AnimatedVisibility(
            enter = EnterTransition.None,
            exit = ExitTransition.None,
            visible = !isSelected
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(fillMaxWidthFloat)
                    .clickable { isSelected = !isSelected },
                elevation = 4.dp,
                shape = RoundedCornerShape(50.dp),
                border =
                    BorderStroke(
                        width = 1.dp,
                        color = isNotSelectedBorderColor
                    )
            ) {
                isNotSelectedContent.invoke()


            }
        }

        AnimatedVisibility(
            enter = EnterTransition.None,
            exit = ExitTransition.None,
            visible = isSelected
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(fillMaxWidthFloat)
                    .clickable { isSelected = !isSelected },
                elevation = 4.dp,
                shape = RoundedCornerShape(50.dp),
                border =
                    BorderStroke(
                        width = 2.dp,
                        color = isSelectedBorderColor
                    )
            ) {
                isSelectedContent.invoke()


            }
        }
    }
}