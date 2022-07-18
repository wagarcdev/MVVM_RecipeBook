package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IngredientsSectionTittleRow(isVisible: Boolean): Boolean {
    var isVisible1 = isVisible
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(40.dp)
            .fillMaxSize()
            .clickable { isVisible1 = !isVisible1 },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Text(
            modifier = Modifier
                .padding(horizontal = 14.dp),
            text = "Ingredients",
            color = Color(0xFF000000),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Icon(
            modifier = Modifier
                .size(30.dp)
                .rotate(
                    if (isVisible1) {
                        0f
                    } else {
                        -90f
                    }
                ),
            imageVector =
            if (isVisible1) {
                Icons.Default.KeyboardArrowDown
            } else {
                Icons.Default.KeyboardArrowDown
            },
            contentDescription = "Arrow Icon"
        )


    }
    return isVisible1
}