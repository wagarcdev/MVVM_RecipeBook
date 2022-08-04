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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.catalog.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Black
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Transparent
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_White
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.ShowAlertDialog
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.VerticalNumberPicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EditRecipeTimeButtonDisplay(
    catalogViewModel: CatalogViewModel,
    coroutineScope: CoroutineScope
) {

    val recipeTime = catalogViewModel.recipeTime.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        val isDialogOpen = remember { mutableStateOf(false) }

        val sizeScale = 40.dp


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
                    catalogViewModel,
                    sizeScale,
                    coroutineScope
                )
            },
            defaultBottomBar = true
        )


        Card(
            modifier = Modifier
                .padding(vertical = 2.dp, horizontal = 4.dp)
                .defaultMinSize(minHeight = 40.dp)
                .clickable { isDialogOpen.value = true },
            elevation = 0.dp,
            shape = RoundedCornerShape(50.dp),
            border =
            BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colors.primary

            ),
            backgroundColor = RB_Transparent
        ) {


            Row(
                modifier = Modifier
                    .padding(vertical = 2.dp, horizontal = 8.dp)
                    .height(25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    modifier = Modifier
                        .height(20.dp),
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = "Tempo de Preparo ",
                    tint = RB_White
                )


                val recipeTimeNullString = " add time"


                AnimatedVisibility(
                    visible = recipeTime.value == 0,
                    enter = EnterTransition.None,
                    exit = ExitTransition.None
                ) {
                    Text(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = RB_White,
                        text = recipeTimeNullString
                    )
                }

                AnimatedVisibility(
                    visible =  recipeTime.value != 0,
                    enter = EnterTransition.None,
                    exit = ExitTransition.None
                ) {
                    Text(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color =  RB_White,
                        text =
                        if ( recipeTime.value < 60) {
                            " ${ recipeTime.value} min"
                        } else {
                            if (( recipeTime.value % 60) == 0) {
                                " ${ recipeTime.value / 60} h"
                            } else {
                                " ${ recipeTime.value / 60} h ${ recipeTime.value % 60} min"
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
    catalogViewModel: CatalogViewModel,
    sizeScale: Dp,
    coroutineScope: CoroutineScope
) {

    val recipeTime = catalogViewModel.recipeTime.collectAsState()

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
                tint =  RB_Black
            )
        }

        //Hours
        VerticalNumberPicker(
            width = sizeScale,
            max = 99,
            default = recipeTime.value.div(60),
            onValueChange = {
                coroutineScope.launch(Dispatchers.Default) {

                    catalogViewModel.changeHours(it)
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
            default = recipeTime.value.rem(60),
            onValueChange = {
                coroutineScope.launch(Dispatchers.Default) {

                    catalogViewModel.changeMinutes(it)

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