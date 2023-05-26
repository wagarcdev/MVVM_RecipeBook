package com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content.main_menu_buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainMenuButton(
    buttonFillMaxWidthFloat: Float = 0.8f,
    buttonVerticalPaddingDp: Dp = 0.dp,
    buttonMinHeight: Dp = 40.dp,
    isLoading: MutableState<Boolean?> = mutableStateOf(false),
    showIcon: Boolean = true,
    iconID: Int,
    iconSize: Dp = 30.dp,
    iconDescription: String = "",
    iconWeight: Float = 0.1f,
    rowVerticalPadding: Dp = 2.dp,
    rowHorizontalPadding: Dp = 8.dp,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    textFontSize: TextUnit = 16.sp,
    textFontColor: Color = Color(0xFFFFFFFF),
    textFillMaxWidthFloat: Float = 0.90f,
    fontWeight: FontWeight = FontWeight.Bold,
    borderStroke: BorderStroke = BorderStroke(2.dp, Color(0xFF1F1F1F)),
    cornerDp: Dp = 50.dp,
    leftGradientColor: Color = Color(0xFF000000),
    centerLeftGradientColor: Color = Color(0xFF1B1B1B),
    centerGradientColor: Color = Color(0xFF1B1B1B),
    centerRightGradientColor: Color = Color(0xFF1B1B1B),
    rightGradientColor: Color = Color(0xFF3F3F3F),
    bottomSpacer: Dp = 8.dp,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .padding(vertical = buttonVerticalPaddingDp)
            .fillMaxWidth(buttonFillMaxWidthFloat)
            .wrapContentHeight()
            .clickable { onClick.invoke() }
            .clip(RoundedCornerShape(cornerDp))
            .background(
                Brush.horizontalGradient(
                    listOf(
                        leftGradientColor,
                        centerLeftGradientColor,
                        centerGradientColor,
                        centerRightGradientColor,
                        rightGradientColor,
                    ),
                )
            ),
        elevation = 0.dp,
        border = borderStroke,
        backgroundColor = Color(0x00000000)

    ) {
        Row(
            modifier = Modifier
                .defaultMinSize(minHeight = buttonMinHeight)
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(
                    vertical = rowVerticalPadding,
                    horizontal = rowHorizontalPadding
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(iconWeight)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (showIcon) {
                    Image(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(iconSize),
                        painter = painterResource(
                            id = iconID
                        ),
                        contentDescription = iconDescription,
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(0.8f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 0.dp)
                        .fillMaxWidth(textFillMaxWidthFloat),
                    text = text,
                    fontSize = textFontSize,
                    fontWeight = fontWeight,
                    color = textFontColor,
                    maxLines = 1,
                    textAlign = textAlign
                )
            }

            val showLoadingIcon = remember { mutableStateOf(isLoading?.value) }

            //DISPLAY ALIGNMENT ROW FOR CENTERING TEXT
            Column(
                modifier = Modifier
                    .weight(iconWeight)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (showLoadingIcon.value == true) {
                    CircularProgressIndicator(
                        Modifier
                            .padding(end = 4.dp)
                            .size(iconSize)
                    )
                }
            }
        }
    }

    Spacer(
        modifier = Modifier
            .height(bottomSpacer)
            .fillMaxWidth(),
    )

}