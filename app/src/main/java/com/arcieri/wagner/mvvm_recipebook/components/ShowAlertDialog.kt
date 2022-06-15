package com.arcieri.wagner.mvvm_recipebook.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.arcieri.wagner.mvvm_recipebook.ui.theme.Purple500

@Composable
fun ShowAlertDialog(
    isDialogOpen: MutableState<Boolean>,
    dialogWidth: Dp = ((LocalConfiguration.current.screenWidthDp / 4 ) * 3 ).dp,
    dialogPadding: Dp = 15.dp,
    dialogShape: Shape = RoundedCornerShape(15.dp),
    dialogColor: Color = Color(0xFFFFFFFF),
    title: String? = null,
    titleSize: TextUnit = 16.sp,
    titleWeight: FontWeight = FontWeight.Bold,
    titleColor: Color = Color(0xFF6200EE),
    defaultBottomBar: Boolean,
    bottomBar: @Composable () -> Unit = { },
    dialogContent: @Composable () -> Unit
) {

    if(isDialogOpen.value) {
        Dialog(onDismissRequest = { isDialogOpen.value = false }) {
            Surface(
                modifier = Modifier
                    .width(dialogWidth)
                    .wrapContentHeight(),
                shape = dialogShape,
                color = dialogColor
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(dialogPadding),
                ) {

                    if(title != null) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(start = 20.dp),
                                text = title,
                                fontSize = titleSize,
                                fontWeight = titleWeight,
                                color = titleColor
                            )
                        }
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        dialogContent.invoke()
                    }



                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        bottomBar.invoke()
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        if (defaultBottomBar) {
                            DefaultButton(isDialogOpen)
                        }
                    }

                }
            }
        }
    }
}

@Composable
private fun DefaultButton(isDialogOpen: MutableState<Boolean>) {
    Button(
        onClick = {
            isDialogOpen.value = false
        },
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(50.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(Purple500)
    ) {
        Text(
            text = "Close",
            color = Color.White,
            fontSize = 12.sp
        )
    }
}



