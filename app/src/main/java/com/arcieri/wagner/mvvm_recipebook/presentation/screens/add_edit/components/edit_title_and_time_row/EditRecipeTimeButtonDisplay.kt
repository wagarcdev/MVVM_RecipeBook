package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_title_and_time_row

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Transparent
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.ShowAlertDialog
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.VerticalNumberPicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EditRecipeTimeButtonDisplay(
    recipeDraft: Recipe,
    coroutineScope: CoroutineScope
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        val isDialogOpen = remember { mutableStateOf(false) }
        val recipeDraftTime = remember { mutableStateOf(recipeDraft.recipeTime) }

        val sizeScale = 40.dp

        val recipeMinutes = remember { mutableStateOf(recipeDraftTime.value) }
        val recipeHours = remember { mutableStateOf(0) }

        LaunchedEffect(key1 = recipeMinutes) {
            if (recipeMinutes.value >= 60) {
                recipeHours.value = recipeMinutes.value / 60
                recipeMinutes.value = recipeMinutes.value % 60
            }
        }

        /**
         *
         *    TODO
         *
         *  Adjust hours and minutes incrementation in separated variables,
         *  then increment recipeDraftTime
         *
         *  */

        /**
         *
         *    TODO
         *
         *  Adjust hours and minutes incrementation in separated variables,
         *  then increment recipeDraftTime
         *
         *  */

        ShowAlertDialog(
            isDialogOpen = isDialogOpen,
            title = "Recipe Preparation Time",
            dialogContent = {
                RecipePrepTimeDialogContent(
                    sizeScale,
                    recipeDraftTime,
                    coroutineScope,
                    recipeHours,
                    recipeMinutes
                )
            },
            defaultBottomBar = true
        )


        Card(
            modifier = Modifier
                .padding(2.dp)
                .height(40.dp)
                .fillMaxWidth(0.3f)
                .clickable { isDialogOpen.value = true },
            elevation = 0.dp,
            shape = RoundedCornerShape(50.dp),
            border =
            BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colors.primary

            ),
            backgroundColor = RB_Transparent
        ) {


            Row(
                modifier = Modifier
                    .height(25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    modifier = Modifier
                        .height(20.dp),
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = "Tempo de Preparo ",
                    tint = Color(0xFF7A7A7A)
                )


                val recipeTimeNullString = " add time"


                AnimatedVisibility(
                    visible = recipeDraftTime.value == 0,
                    enter = EnterTransition.None,
                    exit = ExitTransition.None
                ) {
                    Text(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF7A7A7A),
                        text = recipeTimeNullString
                    )
                }

                AnimatedVisibility(
                    visible = recipeDraftTime.value != 0,
                    enter = EnterTransition.None,
                    exit = ExitTransition.None
                ) {
                    Text(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF7A7A7A),
                        text =
                        if (recipeDraftTime.value < 60) {
                            " ${recipeDraftTime.value}min"
                        } else {
                            if ((recipeDraftTime.value % 60) == 0) {
                                " ${recipeDraftTime.value / 60}h"
                            } else {
                                " ${recipeDraftTime.value / 60}h${recipeDraftTime.value % 60}min"
                            }

                        }

                    )
                }


            }
        }
    }
}

@Composable
private fun RecipePrepTimeDialogContent(
    sizeScale: Dp,
    recipeDraftTime: MutableState<Int>,
    coroutineScope: CoroutineScope,
    recipeHours: MutableState<Int>,
    recipeMinutes: MutableState<Int>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(sizeScale),
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = "Tempo de Preparo ",
                tint = Color(0xFF7A7A7A)
            )
        }

        //Hours
        VerticalNumberPicker(
            width = sizeScale,
            max = 99,
            default = recipeDraftTime.value.div(60),
            onValueChange = {
                coroutineScope.launch(Dispatchers.Default) {
//                                recipeHours.value = recipeHours.value.plus(it)
                    recipeHours.value = it
                    recipeDraftTime.value = (recipeHours.value * 60) + (recipeMinutes.value)
                }
            }
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hour", fontSize = (sizeScale.value / 2).sp)
        }

        //Minutes
        VerticalNumberPicker(
            width = sizeScale,
            max = 60,
            default = recipeDraftTime.value.rem(60),
            onValueChange = {
                coroutineScope.launch(Dispatchers.Default) {
//                                recipeMinutes.value = recipeMinutes.value.plus(it)
                    recipeMinutes.value = it
                    recipeDraftTime.value = (recipeHours.value * 60) + (recipeMinutes.value)
                }

            }
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "min", fontSize = (sizeScale.value / 2).sp)
        }
    }
}