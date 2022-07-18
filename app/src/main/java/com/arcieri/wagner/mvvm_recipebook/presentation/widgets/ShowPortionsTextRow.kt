package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.model.Recipe

@Composable
fun ShowPortionsTextColumn(
    recipe: Recipe,
    colors: Color,
    fontSize: TextUnit = 10.sp,
    iconSize: Dp = 15.dp
) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Portions
            Icon(
                modifier = Modifier
                    .size(iconSize),
                painter = painterResource(id = R.drawable.ic_fork_knife),
                contentDescription = "Rendimento",
                tint = colors
            )

            Text(

                fontSize = fontSize,
                fontWeight = FontWeight.ExtraBold,
                color = colors,
                text = " ${recipe.portions} porções"
            )
        }//Row
    }






}