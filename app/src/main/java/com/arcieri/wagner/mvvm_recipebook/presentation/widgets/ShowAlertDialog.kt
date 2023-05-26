package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main_menu.main_menu_content.main_menu_buttons.MainMenuButton
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.*

@Composable
fun ShowAlertDialog(
    isDialogOpen: MutableState<Boolean>,
    dialogWidthFloat: Float = 0.8F,
    dialogPadding: Dp = 5.dp,
    dialogShape: Shape = RoundedCornerShape(15.dp),
    dialogColor: Color = Color(0xFFFFFFFF),
    title: String? = null,
    titleSize: TextUnit = 16.sp,
    titleWeight: FontWeight = FontWeight.Bold,
    titleColor: Color = Color(0xFF6200EE),
    titleVerticalPadding: Dp = 16.dp,
    defaultBottomBar: Boolean,
    bottomBar: @Composable () -> Unit = { },
    dialogContent: @Composable () -> Unit
) {

    if(isDialogOpen.value) {
        Dialog(onDismissRequest = { isDialogOpen.value = false }) {
            Surface(
                modifier = Modifier
                    .defaultMinSize(minHeight = 450.dp)
                    .fillMaxWidth(),
                shape = dialogShape,
                color = dialogColor
            ) {

                LazyColumn(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 450.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    if(title != null) {
                        item { DialogTitle(title, titleSize, titleWeight, titleColor) }
                    }


                    item { dialogContent.invoke() }


                    item { bottomBar.invoke() }


                    if (defaultBottomBar) {
                        item { DefaultButton(isDialogOpen) }
                    }
                }
            }
        }
    }
}


@Composable
private fun DialogTitle(
    title: String?,
    titleSize: TextUnit,
    titleWeight: FontWeight,
    titleColor: Color
) {
    Text(
        modifier = Modifier
            .padding(start = 20.dp, top = 16.dp, bottom = 16.dp),
        text = title!!,
        fontSize = titleSize,
        fontWeight = titleWeight,
        color = titleColor
    )
}

@Composable
private fun DefaultButton(isDialogOpen: MutableState<Boolean>) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainMenuButton(
            iconID = R.drawable.ic_sign_out,// NOT VISIBLE
            showIcon = false, //SET VISIBILITY
            iconDescription = "Close Button",
            buttonFillMaxWidthFloat = 0.4f,
            text = "CLOSE",
            borderStroke = BorderStroke(0.dp, Color.Transparent),
            leftGradientColor = RB_RedDarker,
            centerLeftGradientColor = RB_RedDark,
            centerGradientColor = RB_RedDark,
            centerRightGradientColor = RB_Red,
            rightGradientColor = RB_RedLight,
            textFontColor = RB_White
        ) { isDialogOpen.value = false }
    }
}



