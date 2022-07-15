package com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.model.Recipe

@Composable
fun ShowPortionsTextColumn(
    recipe: Recipe,
    colors: Color
) {
    Column(
        modifier = Modifier
            .width(85.dp)
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .height(25.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Portions
            Icon(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(id = R.drawable.ic_fork_knife),
                contentDescription = "Rendimento",
                tint = colors
            )

            Text(

                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                color = colors,
                text = " ${recipe.portions} porções"
            )
        }//Row
    }






}