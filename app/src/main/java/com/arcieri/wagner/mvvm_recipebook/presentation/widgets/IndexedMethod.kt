package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Black_25

@Composable
fun IndexedMethod(
    index: Int,
    method: String,
    fontSize: TextUnit = 15.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    fontStyle: FontStyle? = null,
    textColor: Color = Color(0xFF000000),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 10.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        //Ingredient Line
        Row(
            modifier = Modifier
                .padding(top = 2.dp, bottom = 2.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color(0xFF5A5A5A))) {
                        append("${index + 1} - ")
                    }
                    append(method)
                },
                fontWeight = fontWeight,
                fontSize = fontSize,
                fontStyle = fontStyle,
                color = textColor


            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Divider(
            color = RB_Black_25,
            thickness = 1.dp,
        )

        Spacer(modifier = Modifier.height(8.dp))




    }


//    Column(
//        modifier = Modifier
//            .background(backgroundColor)
//            .fillMaxWidth()
//            .padding(horizontal = 10.dp)
//    ) {
//        Text(
//            text = buildAnnotatedString {
//                withStyle(style = SpanStyle(color = Color(0xFF5A5A5A))) {
//                    append("${index + 1} ) ")
//                }
//             append(method)
//            },
//            fontWeight = fontWeight,
//            fontSize = fontSize,
//            fontStyle = fontStyle,
//            color = textColor
//
//
//        )
//    }
//    Divider(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(15.dp),
//        color = Color(0x00000000)
//    )
}