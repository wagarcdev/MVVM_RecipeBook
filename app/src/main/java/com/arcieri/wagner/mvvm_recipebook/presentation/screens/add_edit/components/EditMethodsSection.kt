package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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


        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .height(40.dp)
                .fillMaxSize(),
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
        }

//        val recipeDraftMethods = emptyList<String>().toMutableList()
        val recipeDraftMethods = remember { mutableListOf<String>() }

        recipeDraft.recipeMethods.forEach { method ->
            recipeDraftMethods.add(method)
        }

        recipeDraftMethods.forEachIndexed { index, method ->

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

            Spacer(modifier = Modifier.padding(itemPadding))

        }

        Spacer(modifier = Modifier.padding(itemPadding))

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
            buttonText = "ADD Method",
            minHeight = 60.dp,
            onClick = { isDialogOpen.value = true },
            fillMaxWidthFloat = 0.75f
        )

        Spacer(modifier = Modifier.padding(itemPadding))
        Spacer(modifier = Modifier.padding(itemPadding))

    }
}