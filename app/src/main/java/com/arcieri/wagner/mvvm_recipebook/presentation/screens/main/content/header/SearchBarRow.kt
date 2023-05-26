package com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.content.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.*

@Composable
fun SearchBarRow(
    btnFiltersColor: Color = RB_OrangeDark,
    btnFiltersTxtColor: Color = RB_White
) {

    val layoutHeight = 60.dp

    /** TODO onClick empty/clear search string */
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .height(layoutHeight),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        val sizeAdjustment = 0.dp

        /** TODO onClick empty/clear search string */
        /** TODO onClick empty/clear search string */
        Column(
            modifier = Modifier
                .height(layoutHeight),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val search = remember { mutableStateOf("") }

            /** TODO onClick empty/clear search string */
            OutlinedTextField(
                modifier = Modifier
                    .height(layoutHeight - (sizeAdjustment))
                    .fillMaxWidth(0.8f),
                value = search.value,
                onValueChange = { search.value = it },
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(40.dp),
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "Search Icon",
                        tint = RB_OrangeDark
                    )
                },
                trailingIcon = {
                    /** TODO onClick empty/clear search string */
                    Icon(
                        modifier = Modifier
                            .clickable { search.value = "" },
                        painter = painterResource(R.drawable.ic_cancel),
                        contentDescription = "search filters icon",
                        tint = RB_OrangeDarkNeutral
                    )
                },
                label = {
                    Text(
                        text = "Find a recipe ...",
                        fontWeight = FontWeight.Bold
                    ) },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = RB_White,
                    cursorColor = RB_Orange,
                    focusedLabelColor = RB_Orange,
                    unfocusedLabelColor = RB_White_75,
                    backgroundColor = RB_White_10
                ),
                singleLine = true,
                maxLines = 1,
            )
        }




        Column(
            modifier = Modifier
                .height(layoutHeight)
                .width(layoutHeight),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .size(52.dp),
                backgroundColor = btnFiltersColor,
                shape = RoundedCornerShape(5.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_rule),
                        contentDescription = "search filters icon",
                        tint = RB_White
                    )
                }

            }
        }


    }

    /** SPACER */
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .background(
                Brush.horizontalGradient(
                    listOf(
                        RB_Black_75,
                        RB_Black_75,
                        RB_Black_50,
                        RB_Black_25,
                        RB_Transparent
                    ),
                )
            ),
    )

    /** SPACER */

}