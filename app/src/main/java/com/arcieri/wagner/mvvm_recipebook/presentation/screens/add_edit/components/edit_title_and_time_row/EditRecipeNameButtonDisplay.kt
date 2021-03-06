package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_title_and_time_row

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.RecipeInputText
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.ShowAlertDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EditRecipeNameButtonDisplay(
    itemPadding: Dp,
    recipeDraftTitle: MutableState<String>,
    coroutineScope: CoroutineScope
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val isDialogOpen = remember { mutableStateOf(false) }


        ShowAlertDialog(
            isDialogOpen = isDialogOpen,
            title = "Recipe Name",
            dialogContent = {
                RecipeInputText(
                    modifier = Modifier
                        .padding(itemPadding),
                    text = recipeDraftTitle.value,
                    label = "Recipe Title",
                    textAlignment = TextAlign.End,
                    textFontSize = 22.sp,
                    labelFontSize = 16.sp,
                    maxLines = 4,
                    onTextChange = {
                        coroutineScope.launch(Dispatchers.Default) {
                            recipeDraftTitle.value = it
                        }
                    }
                )
            },
            defaultBottomBar = true
        )

        Card(
            modifier = Modifier
                .height(40.dp)
                .wrapContentWidth()
                .clickable { isDialogOpen.value = true },
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp),
            border =
            BorderStroke(
                width = 1.dp,
                color = Color(0xFF888888)

            )
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = recipeDraftTitle.value,
                    color = Color(0xFF000000),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.End
                )

            }

        }
    }
}