package com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.main_menu_content.main_menu_buttons.units_converter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.ui.theme.RB_IndigoLightExtra
import com.arcieri.wagner.mvvm_recipebook.ui.theme.RB_Transparent
import com.arcieri.wagner.mvvm_recipebook.ui.theme.RB_White


@Composable
fun ConverterRow(
    showIcon: Boolean = true,
    iconID: Int,
    iconSize: Dp = 30.dp,
    iconDescription: String = "",
    rowVerticalPadding: Dp = 0.dp,
    rowHorizontalPadding: Dp = 8.dp,
    unit: String,
    description: String,
    textAlign: TextAlign = TextAlign.Center,
    unityQuantityText: String = "0",  // TODO remove initialization
    descFontColor: Color = Color(0xFFFFFFFF),
    unitFontColor: Color = Color(0xFFFFFFFF),
    fontSize: TextUnit = 16.sp,
    descFontSize: TextUnit = 10.sp,
    dividerColor: Color = RB_White,
    dividerThickness: Dp = 1.dp,
    hasDivider: Boolean = true
) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .defaultMinSize(minHeight = 35.dp)
                .wrapContentHeight()
                .fillMaxWidth(0.9f)
                .padding(
                    vertical = rowVerticalPadding,
                    horizontal = rowHorizontalPadding
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            /** ICON */
            Column(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .wrapContentWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (showIcon) {
                    Image(
                        modifier = Modifier
                            .size(iconSize),
                        painter = painterResource(
                            id = iconID
                        ),
                        contentDescription = iconDescription,
                    )
                }

            }

            val textField = remember { mutableStateOf(unityQuantityText) }








            /** DESCRIPTION */
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .width(90.dp)
                        .padding(horizontal = 8.dp),
                    text = description,
                    fontSize = descFontSize,
                    fontWeight = FontWeight.Bold,
                    color =
                    if (unit.isNotEmpty() && unit.isNotBlank()) {
                        descFontColor
                    } else {
                        unitFontColor
                    },
                    maxLines = 1,
                    textAlign = textAlign
                )
            }

            /** INPUT FIELD */

            Column(
                modifier = Modifier
                    .wrapContentWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    modifier = Modifier
                        .width(60.dp)
                        .wrapContentHeight(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = RB_Transparent,
                        textColor = RB_White,
                        cursorColor = RB_IndigoLightExtra,
                        focusedIndicatorColor = RB_Transparent,
                        unfocusedIndicatorColor = RB_Transparent,
                        focusedLabelColor = RB_Transparent,
                        unfocusedLabelColor = RB_Transparent,

                        ),
                    singleLine = true,
                    maxLines = 1,
                    visualTransformation = VisualTransformation.None,
                    value = textField.value,
                    textStyle = TextStyle(
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold
                    ),
                    onValueChange ={
                        // TODO coroutine to change value in ViewModel
                    } )
            }



            /** Unit */

            if (unit.isNotEmpty() && unit.isNotBlank()) {

                Column(
                    modifier = Modifier
                        .width(60.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 0.dp),
                        text = unit,
                        fontSize = fontSize,
                        fontWeight = FontWeight.Bold,
                        color = unitFontColor,
                        maxLines = 1,
                        textAlign = TextAlign.Left
                    )
                }
            }


        }

        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(0.85f)
        ) {

            if(hasDivider) {
                Divider(
                    color = dividerColor,
                    thickness = dividerThickness
                )
            }


        }

        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth(),
        )
    }
}