package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SelectOptionAlertDialogButton(
    cardHeight: Dp,
    cardWidth: Dp,
    cardColor: Color = Color(0xFFFFFFFF),
    cardBorderStroke: BorderStroke = BorderStroke(1.dp, Color(0xFF888888)),
    cardShape: Shape = RoundedCornerShape(50.dp),
    iconID: Int,
    iconSize: Dp,
    iconColor: Color = Color(0xFF000000),
    textColor:Color = Color(0xFF000000),
    textFontWeight: FontWeight = FontWeight.Bold,
    contentDesc: String,
    buttonText: String,
    onClick: () -> Unit

) {
    Card(
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight)
            .clickable { onClick.invoke() },
        shape = cardShape,
        elevation = 4.dp,
        border = cardBorderStroke,
        backgroundColor = cardColor
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(iconSize),
                painter = painterResource(iconID),
                contentDescription = contentDesc,
                tint = iconColor
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = buttonText,
                color = textColor,
                fontWeight = textFontWeight
            )

        }

    }
}