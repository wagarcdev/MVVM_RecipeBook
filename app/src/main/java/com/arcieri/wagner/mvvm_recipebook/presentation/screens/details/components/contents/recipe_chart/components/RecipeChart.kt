package com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.contents.recipe_details

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.utils.RandomColors

@Composable
fun RecipeChart(
    points: List<Float>,
    colors: List<Color>,
    sizeDp: Dp = 100.dp
    ) {

    val totalPercentage = points.sum()
    val proportions = points.map { point ->
        point * 100 / totalPercentage
    }

    val sweepAnglePercentage = proportions.map { proportion ->
        360 * proportion / 100
    }



    Canvas(modifier = Modifier.fillMaxSize()) {

        var startAngle = 270f

        sweepAnglePercentage.forEachIndexed { index, sweepAngle ->
            drawChartArc(
                color = colors[index],
                startAngle = startAngle,
                sweepAngle = sweepAngle
            )

            startAngle += sweepAngle
        }
    }



    
}

fun DrawScope.drawChartArc(
    color: Color,
    startAngle: Float,
    sweepAngle: Float
) {

    val padding = 300f

    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        size = Size(width = size.width - padding, height = size.width - padding),
        style = Stroke (width = 100f),
        topLeft = Offset(x = padding/2f, y = padding/2f)
    )
}


@Preview
@Composable
fun RecipeChartPreview() {

    val list = listOf(25f, 30f, 15f, 10f, 200f, 45f, 700f)

    val colorList: MutableList<Color> = arrayListOf()

    list.forEach { float ->
        colorList.add(RandomColors().color)
    }

    RecipeChart(
        points = list,
        colors = colorList

    )
}