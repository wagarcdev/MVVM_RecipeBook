package com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.content.fab

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.*
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.RoundButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MainScreenFAB() {

    val showMenuItems = remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val menuIcon = remember { mutableStateOf(R.drawable.ic_menu) }

    val leftGradientColor = remember { mutableStateOf(RB_OrangeDarker) }
    val centerLeftGradientColor = remember { mutableStateOf(RB_OrangeDark) }
    val centerGradientColor = remember { mutableStateOf(RB_OrangeDark) }
    val centerRightGradientColor = remember { mutableStateOf(RB_Orange) }
    val rightGradientColor = remember { mutableStateOf(RB_OrangeLight) }
    val borderColor = remember { mutableStateOf(RB_Transparent) }
    val iconColor = remember { mutableStateOf(RB_White) }

    val fontWeight = FontWeight.SemiBold

    val duration = 8000

    val fontSize = 15.sp


    Box(
        modifier = Modifier
            .wrapContentSize()
            .clickable {
                coroutineScope.launch(Dispatchers.Default) {
                    showMenuItems.value = !showMenuItems.value
                    changeMenuIcon(
                        menuIcon,
                        leftGradientColor,
                        centerLeftGradientColor,
                        centerGradientColor,
                        centerRightGradientColor,
                        rightGradientColor,
                        borderColor,
                        iconColor,
                        coroutineScope
                    )
                }
            }
    ) {

        if (showMenuItems.value) {
            FabMenuButton(
                lineOneText = "NEW",
                lineTwoText = "Recipe",
                fontSize,
                fontWeight,
                coroutineScope,
                showMenuItems,
                menuIcon,
                leftGradientColor,
                centerLeftGradientColor,
                centerGradientColor,
                centerRightGradientColor,
                rightGradientColor,
                borderColor,
                iconColor,
                boxOffsetPosY = -(60).dp,
                boxOffsetPosX = 25.dp,
                cardOffsetPosX = -(0).dp,
                cardOffsetPosY = -(35).dp
            )

        }

//        AnimatedVisibility(
//            visible = showMenuItems.value,
//            enter = EnterTransition.None,
//            exit = ExitTransition.None
//        ) {
//            FabMenuButton(
//                lineOneText = "NEW",
//                lineTwoText = "Recipe",
//                fontSize,
//                fontWeight,
//                coroutineScope,
//                showMenuItems,
//                menuIcon,
//                leftGradientColor,
//                centerLeftGradientColor,
//                centerGradientColor,
//                centerRightGradientColor,
//                rightGradientColor,
//                borderColor,
//                iconColor,
//                boxOffsetPosY = -(60).dp,
//                boxOffsetPosX = 25.dp,
//                cardOffsetPosX = -(0).dp,
//                cardOffsetPosY = -(35).dp
//            )
//
//        }

        if (showMenuItems.value) {
            FabMenuButton(
                lineOneText = "Assemble",
                lineTwoText = "Menu",
                fontSize,
                fontWeight,
                coroutineScope,
                showMenuItems,
                menuIcon,
                leftGradientColor,
                centerLeftGradientColor,
                centerGradientColor,
                centerRightGradientColor,
                rightGradientColor,
                borderColor,
                iconColor,
                boxOffsetPosY = -(20).dp,
                boxOffsetPosX = (25).dp,
                cardOffsetPosX = -(105).dp,
                cardOffsetPosY = -(70).dp
            )
        }

//        AnimatedVisibility(
//            visible = showMenuItems.value,
//            enter = EnterTransition.None,
//            exit = ExitTransition.None
//        ) {
//            FabMenuButton(
//                lineOneText = "Assemble",
//                lineTwoText = "Menu",
//                fontSize,
//                fontWeight,
//                coroutineScope,
//                showMenuItems,
//                menuIcon,
//                leftGradientColor,
//                centerLeftGradientColor,
//                centerGradientColor,
//                centerRightGradientColor,
//                rightGradientColor,
//                borderColor,
//                iconColor,
//                boxOffsetPosY = -(20).dp,
//                boxOffsetPosX = (25).dp,
//                cardOffsetPosX = -(105).dp,
//                cardOffsetPosY = -(70).dp
//            )
//        }

        if (showMenuItems.value) {
            FabMenuButton(
                lineOneText = "SELECT",
                lineTwoText = "Multiple",
                fontSize,
                fontWeight,
                coroutineScope,
                showMenuItems,
                menuIcon,
                leftGradientColor,
                centerLeftGradientColor,
                centerGradientColor,
                centerRightGradientColor,
                rightGradientColor,
                borderColor,
                iconColor,
                boxOffsetPosY = -(60).dp,
                boxOffsetPosX = -(25).dp,
                cardOffsetPosX = -(120).dp,
                cardOffsetPosY = 15.dp
            )
        }


//        AnimatedVisibility(
//            visible = showMenuItems.value,
//            enter = EnterTransition.None,
//            exit = ExitTransition.None
//        ) {
//            FabMenuButton(
//                lineOneText = "SELECT",
//                lineTwoText = "Multiple",
//                fontSize,
//                fontWeight,
//                coroutineScope,
//                showMenuItems,
//                menuIcon,
//                leftGradientColor,
//                centerLeftGradientColor,
//                centerGradientColor,
//                centerRightGradientColor,
//                rightGradientColor,
//                borderColor,
//                iconColor,
//                boxOffsetPosY = -(60).dp,
//                boxOffsetPosX = -(25).dp,
//                cardOffsetPosX = -(120).dp,
//                cardOffsetPosY = 15.dp
//            )
//        }

        RoundButton(
            modifier = Modifier
                .padding(start = 15.dp, end = 0.dp, top = 0.dp, bottom = 50.dp)
                .offset(y = -(0).dp, x = -(0).dp),
            buttonSize = 70.dp,
            onClick = {
                coroutineScope.launch(Dispatchers.Default) {
                    showMenuItems.value = !showMenuItems.value
                    changeMenuIcon(
                        menuIcon,
                        leftGradientColor,
                        centerLeftGradientColor,
                        centerGradientColor,
                        centerRightGradientColor,
                        rightGradientColor,
                        borderColor,
                        iconColor,
                        coroutineScope
                    )
                }
            },
            buttonShape = RoundedCornerShape(15.dp),
            leftGradientColor = leftGradientColor.value,
            centerLeftGradientColor = centerLeftGradientColor.value,
            centerGradientColor = centerGradientColor.value,
            centerRightGradientColor = centerRightGradientColor.value,
            rightGradientColor = rightGradientColor.value,
            borderColor = borderColor.value,
            borderStroke = BorderStroke(0.dp, RB_Transparent),
            elevation = 0.dp,
            iconID = menuIcon.value,
            iconSize = 48.dp,
            iconDescription = "EDIT Recipe Button",
            iconColor = iconColor.value
        )
    }
}

@Composable
private fun FabMenuButton(
    lineOneText: String,
    lineTwoText: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    coroutineScope: CoroutineScope,
    showMenuItems: MutableState<Boolean>,
    menuIcon: MutableState<Int>,
    leftGradientColor: MutableState<Color>,
    centerLeftGradientColor: MutableState<Color>,
    centerGradientColor: MutableState<Color>,
    centerRightGradientColor: MutableState<Color>,
    rightGradientColor: MutableState<Color>,
    borderColor: MutableState<Color>,
    iconColor: MutableState<Color>,
    boxOffsetPosY: Dp,
    boxOffsetPosX: Dp,
    cardOffsetPosX: Dp,
    cardOffsetPosY: Dp
) {
    Box(
        modifier = Modifier
            .padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 0.dp)
            .offset(y = boxOffsetPosY, x = boxOffsetPosX)
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 0.dp)
                .offset(y = cardOffsetPosY, x = cardOffsetPosX),
            backgroundColor = RB_White
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 6.dp, end = 6.dp, top = 1.dp, bottom = 0.dp),
                    text = lineOneText,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .padding(start = 6.dp, end = 6.dp, top = 0.dp, bottom = 1.dp),
                    text = lineTwoText,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    textAlign = TextAlign.Center
                )
            }

        }

        RoundButton(
            modifier = Modifier
                .padding(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 0.dp)
                .offset(y = (5).dp, x = -(0).dp),
            buttonSize = 55.dp,
            onClick = {
                coroutineScope.launch(Dispatchers.Default) {
                    showMenuItems.value = !showMenuItems.value
                    changeMenuIcon(
                        menuIcon,
                        leftGradientColor,
                        centerLeftGradientColor,
                        centerGradientColor,
                        centerRightGradientColor,
                        rightGradientColor,
                        borderColor,
                        iconColor,
                        coroutineScope
                    )
                }
            },
            leftGradientColor = RB_OrangeDarker,
            centerLeftGradientColor = RB_OrangeDark,
            centerGradientColor = RB_OrangeDark,
            centerRightGradientColor = RB_Orange,
            rightGradientColor = RB_OrangeLight,
            borderColor = RB_Transparent,
            elevation = 0.dp,
            iconID = R.drawable.ic_add_circle,
            iconSize = 35.dp,
            iconDescription = "EDIT Recipe Button",
            iconColor = RB_White
        )
    }
}


private fun changeMenuIcon(
    menu_icon: MutableState<Int>,
    leftGradientColor: MutableState<Color>,
    centerLeftGradientColor: MutableState<Color>,
    centerGradientColor: MutableState<Color>,
    centerRightGradientColor: MutableState<Color>,
    rightGradientColor: MutableState<Color>,
    borderColor: MutableState<Color>,
    iconColor: MutableState<Color>,
    coroutineScope: CoroutineScope
) {
    if (menu_icon.value == R.drawable.ic_menu) {
        coroutineScope.launch {
            menu_icon.value = R.drawable.ic_menu_expanded_2

            leftGradientColor.value = RB_Transparent
            centerLeftGradientColor.value = RB_Transparent
            centerGradientColor.value = RB_Transparent
            centerRightGradientColor.value = RB_Transparent
            rightGradientColor.value = RB_Transparent
            borderColor.value = RB_Orange
            iconColor.value = RB_Orange_50

        }
    } else {
        menu_icon.value = R.drawable.ic_menu

        leftGradientColor.value = RB_OrangeDarker
        centerLeftGradientColor.value = RB_OrangeDark
        centerGradientColor.value = RB_OrangeDark
        centerRightGradientColor.value = RB_Orange
        rightGradientColor.value = RB_OrangeLight
        borderColor.value = RB_Transparent
        iconColor.value = RB_White
    }
}

