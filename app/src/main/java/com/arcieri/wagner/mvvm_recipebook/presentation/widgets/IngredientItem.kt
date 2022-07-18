package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.data.RecipeData
import com.arcieri.wagner.mvvm_recipebook.model.Ingredient
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Black
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Transparent
import java.text.DecimalFormat
import kotlin.math.floor

@Composable
fun IngredientItem(
    ingredient: Ingredient,
    fontSize: TextUnit = 12.sp,
    fontColor: Color = RB_Black,
    fontWeight: FontWeight = FontWeight.Normal,
    backgroundColor: Color = RB_Transparent
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 10.dp)
            .wrapContentHeight()
            .background(backgroundColor),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        //Ingredient Line
        Row(
            modifier = Modifier
                .padding(top = 2.dp, bottom = 2.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {




            //Quantity
            Column(
                modifier = Modifier
                    .wrapContentWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                Row {

                    //Check if Ingredient have its own measure or how to use text
                    if (ingredient.textHowToMeasure != null) {

                        //Ingredient text How To Use/Measure
                        Column(
                            modifier = Modifier
                                .width(85.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                textAlign = TextAlign.Center,
                                text = ingredient.textHowToMeasure!!,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                color = fontColor,
                                fontSize = fontSize,
                                fontWeight = fontWeight
                            )
                        }




                        //Show ingredient quantity
                    } else {

                        //Quantity
                        Column(
                            modifier = Modifier
                                .width(40.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.End
                        ) {

                            val df = DecimalFormat("##")
                            Text(
                                textAlign = TextAlign.Center,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                color = fontColor,
                                fontSize = fontSize,
                                fontWeight = fontWeight,
                                text =
                                when {
                                    ingredient.isUnit -> {
                                        if (ingredient.quantity ==
                                            ingredient.quantity?.let {floor(it)}) {

                                            "${ingredient.quantity?.toBigDecimal()?.setScale(0)}"

                                        } else {

                                            "${ingredient.quantity}"
                                        }
                                    }
                                    ingredient.isLiquid -> {
                                        if (ingredient.volumeInMilliliters!! >= 1000) {
                                            if (ingredient.volumeInLiters ==
                                                ingredient.volumeInLiters?.let {floor(it)}) {

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
                                            if (ingredient.weightInKg ==
                                                ingredient.weightInKg?.let {floor(it)}) {

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
                                .width(40.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                textAlign = TextAlign.Center,
                                fontStyle = FontStyle.Italic,
                                color = fontColor,
                                fontSize = fontSize,
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
                    }//else


                }//row

            }//Column - Ingredient Info

            //Ingredient Name
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .padding(horizontal = 6.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = ingredient.name,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    color = fontColor
                )
            }//Ingredient Name



        }//Line - texts



    }//Ingredient Column
}//Fun


@Preview(showBackground = true)
@Composable
fun IngredientItemPreview() {

    val recipe = RecipeData().loadRecipe(LocalContext.current)

    IngredientItem(ingredient = recipe.ingredients!![0])
}