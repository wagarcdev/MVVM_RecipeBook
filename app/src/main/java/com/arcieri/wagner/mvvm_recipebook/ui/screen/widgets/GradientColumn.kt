package com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.ui.theme.RB_Transparent

@Composable
fun GradientColumn(
    modifier: Modifier = Modifier,
    columnHorizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    columnVerticalArrangement: Arrangement.Vertical = Arrangement.Bottom,
    topSpacerTransition: Boolean,
    topSpacerTransitionHeight: Dp = 0.dp,
    topTransitionTopColor: Color = RB_Transparent,
    topTransitionMiddleColor: Color = RB_Transparent,
    topTransitionBottomColor: Color = RB_Transparent,
    bottomSpacerTransition: Boolean,
    bottomSpacerTransitionHeight: Dp = 0.dp,
    bottomTransitionTopColor: Color = RB_Transparent,
    bottomTransitionMiddleColor: Color = RB_Transparent,
    bottomTransitionBottomColor: Color = RB_Transparent,
    columnTopColor: Color = RB_Transparent,
    columnMiddleColor: Color = RB_Transparent,
    columnBottomColor: Color = RB_Transparent,
    contentHorizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    contentVerticalArrangement: Arrangement.Vertical =  Arrangement.SpaceBetween,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = columnHorizontalAlignment,
        verticalArrangement = columnVerticalArrangement
    ) {


        if (topSpacerTransition) {
            GradientTransitionRows(
                transitionHeight = topSpacerTransitionHeight,
                topColor = topTransitionTopColor,
                middleColor = topTransitionMiddleColor,
                bottomColor = topTransitionBottomColor
            )
        }



        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            columnTopColor,
                            columnMiddleColor,
                            columnBottomColor
                        ),
                    )
                ),
            horizontalAlignment = contentHorizontalAlignment,
            verticalArrangement = contentVerticalArrangement
        ) {

            content.invoke()
        }

        if (bottomSpacerTransition) {
            GradientTransitionRows(
                transitionHeight = bottomSpacerTransitionHeight,
                topColor = bottomTransitionTopColor,
                middleColor = bottomTransitionMiddleColor,
                bottomColor = bottomTransitionBottomColor
            )

        }


    }
}

@Composable
private fun GradientTransitionRows(
    transitionHeight: Dp,
    topColor: Color,
    middleColor: Color,
    bottomColor: Color
) {

    val rowHeight = transitionHeight / 2

    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .height(rowHeight)
            .background(
                Brush.verticalGradient(
                    listOf(
                        topColor,
                        middleColor
                    ),
                )
            ),
    ) { }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(rowHeight)
            .background(
                Brush.verticalGradient(
                    listOf(
                        middleColor,
                        bottomColor
                    ),
                )
            )
    ) { }
}