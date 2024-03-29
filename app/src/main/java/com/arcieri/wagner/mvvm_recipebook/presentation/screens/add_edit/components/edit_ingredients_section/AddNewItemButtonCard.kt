package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_ingredients_section

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Transparent
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_White

/**
 *
 *
 *   Input and Buttons
 *
 * */
@Composable
fun AddNewItemButtonCard(
    buttonText: String,
    fillMaxWidthFloat: Float = 1f,
    minHeight: Dp = 60.dp,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .defaultMinSize(minHeight = minHeight)
            .fillMaxWidth(fillMaxWidthFloat)
            .clickable {
                onClick.invoke()
            },
        elevation = 5.dp,
        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(2.dp, MaterialTheme.colors.primary),
        backgroundColor = RB_Transparent
    ) {
        Row(
            modifier = Modifier
                .height(35.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 35.dp, end = 20.dp)
                    .size(35.dp),
                imageVector = Icons.Rounded.AddCircle,
                contentDescription = "Add Button Icon",
                tint = MaterialTheme.colors.primary
            )
            Text(
                text = buttonText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = RB_White
            )
        }
    }
}