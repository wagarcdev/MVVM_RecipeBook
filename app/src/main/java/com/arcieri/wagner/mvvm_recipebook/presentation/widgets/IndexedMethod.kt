package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IndexedMethod(
    index: Int,
    method: String,
    fontSize: TextUnit = 15.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    fontStyle: FontStyle? = null,
    textColor: Color = Color(0xFF000000)
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xFF5A5A5A))) {
                    append("${index + 1} ) ")
                }
             append(method)
            },
            fontWeight = fontWeight,
            fontSize = fontSize,
            fontStyle = fontStyle,
            color = textColor


        )
    }
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(15.dp),
        color = Color(0x00000000)
    )
}