package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_ingredients_section.AddNewItemButtonCard
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_methods_section.MethodIsNotSelectedContent
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_methods_section.MethodIsSelectedContent
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.AnimatedCardView
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.RecipeInputText
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.ShowAlertDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EditMethodsSection(
    recipeDraft: Recipe,
    itemPadding: Dp
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.padding(itemPadding))

        var isVisible by remember { mutableStateOf(true) }

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
                text = "Preparation Methods",
                color = Color(0xFF000000),
                fontSize = 20.sp,
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

//        val recipeDraftMethods = emptyList<String>().toMutableList()
        val recipeDraftMethods = remember { mutableListOf<String>() }

        recipeDraft.recipeMethods.forEach { method ->
            recipeDraftMethods.add(method)
        }

        recipeDraftMethods.forEachIndexed { index, method ->

            AnimatedVisibility(
                visible = isVisible,
                enter = EnterTransition.None,
                exit = ExitTransition.None
            ) {
                AnimatedCardView(
                    isNotSelectedContent = {
                        MethodIsNotSelectedContent(
                            itemPadding,
                            index,
                            method
                        )
                    },
                    isSelectedContent = { MethodIsSelectedContent(itemPadding, method) },
                    isSelectedBorderColor = Color(0xFF0022A3),
                    fillMaxWidthFloat = 0.9f
                )
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = EnterTransition.None,
                exit = ExitTransition.None
            ) {
                Spacer(modifier = Modifier.padding(itemPadding))
            }


        }

        AnimatedVisibility(
            visible = isVisible,
            enter = EnterTransition.None,
            exit = ExitTransition.None
        ) {
            Spacer(modifier = Modifier.padding(itemPadding))
        }

        AnimatedVisibility(
            visible = isVisible,
            enter = EnterTransition.None,
            exit = ExitTransition.None
        ) {

            val isDialogOpen = remember { mutableStateOf(false) }
            val newMethod = remember { mutableStateOf("") }
            val coroutineScope = rememberCoroutineScope()

            ShowAlertDialog(
                title = "Add method",
                isDialogOpen = isDialogOpen,
                dialogContent = {
                    Surface() {
                        Row() {
                            RecipeInputText(
                                text = newMethod.value,
                                label = "new method",
                                onTextChange = { newMethod.value = it })
                        }
                    }
                },
                defaultBottomBar = true,
                bottomBar = {
                    Button(
                        onClick = {
                            coroutineScope.launch(Dispatchers.Default) {
                                recipeDraftMethods.add(newMethod.value)
                            }
                            isDialogOpen.value = false
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(50.dp)
                            .padding(10.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF0022A3))
                    ) {
                        Text(
                            text = "ADD Method",
                            color = Color(0xFFFFFFFF),
                            fontSize = 12.sp
                        )
                    }
                }
            )

            AddNewItemButtonCard(
                buttonText = "Method",
                minHeight = 70.dp,
                onClick = { isDialogOpen.value = true },
                fillMaxWidthFloat = 0.9f
            )
        }

        Spacer(modifier = Modifier.padding(itemPadding))
        Spacer(modifier = Modifier.padding(itemPadding))

    }
}