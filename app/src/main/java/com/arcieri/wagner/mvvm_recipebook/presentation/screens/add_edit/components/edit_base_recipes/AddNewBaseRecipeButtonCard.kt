package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_base_recipes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Transparent
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_White

@Composable
fun AddNewBaseRecipeButtonCard(
    cardBackgroundColor:Color = RB_Transparent,
    fontColor: Color = RB_White,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .padding(2.dp)
            .size(132.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, MaterialTheme.colors.primary),
        elevation = 5.dp,
        backgroundColor = cardBackgroundColor,
    ) {

        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                modifier = Modifier
                    .padding(start = 0.dp, end = 0.dp)
                    .size(35.dp),
                imageVector = Icons.Rounded.AddCircle,
                contentDescription = "Add Button Icon",
                tint = MaterialTheme.colors.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "ADD",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = fontColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "Base Recipe",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = fontColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
    }

}