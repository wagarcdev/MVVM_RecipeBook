package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SectionTitleRow(
    text: String,
    fontSize: TextUnit = 20.sp,
    isSectionVisible: Boolean = true
) {

    var isVisible by remember { mutableStateOf(isSectionVisible) }

    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(40.dp)
            .fillMaxSize()
            .clickable { isVisible = !isVisible },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Text(
            modifier = Modifier
                .padding(horizontal = 14.dp),
            text = text,
            color = Color(0xFF000000),
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )

        Icon(
            modifier = Modifier
                .size(30.dp)
                .rotate(
                    if (isVisible) {
                        0f
                    } else {
                        -90f
                    }
                ),
            imageVector =
            if (isVisible) {
                Icons.Default.KeyboardArrowDown
            } else {
                Icons.Default.KeyboardArrowDown
            },
            contentDescription = "Arrow Icon"
        )



    }
}




@Preview(showBackground = true)
@Composable
fun SectionTitleRowPreview() {

    SectionTitleRow("Modo de Preparo")

}