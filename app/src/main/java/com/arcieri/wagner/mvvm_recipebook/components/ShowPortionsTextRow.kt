package com.arcieri.wagner.mvvm_recipebook.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun ShowPortionsTextRow(recipe: Recipe) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(25.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //Portions
        Text(
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF7A7A7A),
            text = "Rendimento "
        )

        Icon(
            modifier = Modifier
                .height(20.dp),
            painter = painterResource(id = R.drawable.ic_fork_knife),
            contentDescription = "Rendimento",
            tint = Color(0xFF7A7A7A)
        )

        Text(

            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF7A7A7A),
            text = " ${recipe.portions} porções"
        )
    }//Row




}