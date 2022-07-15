package com.arcieri.wagner.mvvm_recipebook.ui.screen.add_edit.components.edit_methods_section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets.RecipeInputText

@Composable
fun MethodIsSelectedContent(
    itemPadding: Dp,
    method: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .wrapContentHeight()
                .padding(10.dp)
        ) {
            RecipeInputText(
                modifier = Modifier
                    .padding(itemPadding),
                text = method,
                textAlignment = TextAlign.Center,
                textFontSize = 22.sp,
                labelFontSize = 16.sp,
                maxLines = 15,
                onTextChange = {}
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(45.dp)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        /* TODO */
                    },
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Icon",
                tint = Color(0xFFC00000)

            )
        }
    }
}