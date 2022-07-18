package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_ingredients_section

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.model.Ingredient
import java.text.DecimalFormat
import kotlin.math.floor

/**   Input and Buttons  */


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun IngredientIsSelectedContent(
    ingredient: Ingredient,
    textSize: TextUnit = 12.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    onTextQuantityChange: (String) -> Unit,

    ) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {


        Column(
            modifier = Modifier
                .wrapContentHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            //Ingredient Line
            Row(
                modifier = Modifier
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {


                //Ingredient Info
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.3f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {


                    Row {

                        //Check if Ingredient have its own measure or how to use text
                        if (ingredient.textHowToMeasure != null) {


                            //Ingredient text How To Use/Measure
                            Column(
                                modifier = Modifier,
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    modifier = Modifier
                                        .width(80.dp)
                                        .height(50.dp)
                                ) {

                                    TextField(
                                        modifier = Modifier
                                            .height(50.dp),
                                        value = ingredient.textHowToMeasure!!,
                                        onValueChange = {},
                                        singleLine = true,
                                        colors = TextFieldDefaults.textFieldColors(
                                            backgroundColor = Color(
                                                0x00FFFFFF
                                            )
                                        ),
                                        keyboardOptions = KeyboardOptions.Default.copy(
                                            imeAction = ImeAction.Done
                                        ),
                                        keyboardActions = KeyboardActions(onDone = {
                                            keyboardController?.hide()
                                        }),
                                        textStyle = TextStyle(
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                    )
                                }


                            }


                            //Show ingredient quantity
                        } else {

                            Row() {
                                //Quantity
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.End
                                ) {

                                    val df = DecimalFormat("##")


                                    Text(
                                        textAlign = TextAlign.Center,
                                        fontStyle = FontStyle.Italic,
                                        color = Color(0xFF2C2C2C),
                                        fontSize = textSize,
                                        fontWeight = fontWeight,
                                        text =
                                        when {
                                            ingredient.isUnit -> {
                                                if (ingredient.quantity == floor(ingredient.quantity!!)) {
                                                    df.format(ingredient.quantity)
                                                } else {
                                                    "${ingredient.quantity}"
                                                }
                                            }
                                            ingredient.isLiquid -> {
                                                if (ingredient.volumeInMilliliters!! >= 1000) {
                                                    if (ingredient.volumeInLiters == floor(
                                                            ingredient.volumeInLiters!!
                                                        )
                                                    ) {
                                                        df.format(ingredient.volumeInLiters)
                                                    } else {
                                                        "${ingredient.volumeInLiters}"
                                                    }
                                                } else {
                                                    "${ingredient.volumeInMilliliters}"
                                                }
                                            }
                                            ingredient.isWeight -> {
                                                if (ingredient.weightInGrams!! >= 1000) {
                                                    if (ingredient.weightInKg == floor(
                                                            ingredient.weightInKg!!
                                                        )
                                                    ) {
                                                        df.format(ingredient.weightInKg)
                                                    } else {
                                                        "${ingredient.weightInKg}"
                                                    }
                                                } else {
                                                    "${ingredient.weightInGrams}"
                                                }

                                            }
                                            else -> {
                                                ""
                                            }
                                        }
                                    )
                                }

                                Divider(
                                    modifier = Modifier
                                        .width(5.dp),
                                    color = Color(0x00FFFFFF)
                                )

                                //Unit
                                Column(
                                    modifier = Modifier
                                        .width(60.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.Start
                                ) {
                                    Text(
                                        textAlign = TextAlign.Center,
                                        fontStyle = FontStyle.Italic,
                                        color = Color(0xFF2C2C2C),
                                        fontSize = textSize,
                                        fontWeight = fontWeight,
                                        text =
                                        when {
                                            ingredient.isUnit -> {
                                                " Un."
                                            }
                                            ingredient.isLiquid -> {
                                                if (ingredient.volumeInMilliliters!! >= 1000) {
                                                    " L"
                                                } else {
                                                    " ml"
                                                }

                                            }
                                            ingredient.isWeight -> {
                                                if (ingredient.weightInGrams!! >= 1000) {
                                                    " Kg"
                                                } else {
                                                    " g"
                                                }
                                            }
                                            else -> {
                                                ""
                                            }
                                        }
                                    )

                                }//Column

                            }//row
                        }//else


                    }//row

                }//Column - Ingredient Info

                //Ingredient Name
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(horizontal = 6.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {

                    TextField(
                        modifier = Modifier
                            .height(50.dp),
                        value = ingredient.name,
                        onValueChange = {},
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color(
                                0x00FFFFFF
                            )
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            keyboardController?.hide()
                        }),
                        textStyle = TextStyle(fontSize = 12.sp),
                    )


                }//Ingredient Name


            }//Line - texts


        }//Ingredient Column

        Icon(
            modifier = Modifier
                .padding(end = 25.dp)
                .size(25.dp)
                .clickable {
                    /* TODO */
                },
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Icon",
            tint = Color(0xFFC00000)

        )
    }

}