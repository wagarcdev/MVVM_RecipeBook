package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_base_recipes.AddNewBaseRecipeButtonCard
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Orange_50
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Transparent
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_White
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.RecipeImageAndInfo
import com.arcieri.wagner.mvvm_recipebook.utils.colorList_1_4_2blacks

@Composable
fun EditBaseRecipesSection(
    catalogViewModel: CatalogViewModel,
    itemPadding: Dp,
    fontColor: Color = RB_White
) {


    val cardBackgroundColor = RB_Transparent

    val height = 40.dp

    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(height)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            text = "Bases para ${catalogViewModel.currentRecipe?.name}",
            color = fontColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }

    val baseRecipes: MutableList<Recipe> = emptyList<Recipe>().toMutableList()
    val context = LocalContext.current

    catalogViewModel.currentRecipe?.baseRecipes?.forEach { base ->

        baseRecipes += catalogViewModel.recipeList.collectAsState().value.filter { it.name == base }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(RB_Transparent),
        color = RB_Transparent,
        contentColor = RB_Transparent,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
        ) {

            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .height(140.dp)
            ) {

                item {
                    AddNewBaseRecipeButtonCard(
                        cardBackgroundColor = cardBackgroundColor,
                        fontColor = fontColor,
                        onClick = {

                        },


                        )
                    Spacer(modifier = Modifier.padding(itemPadding))

                }

                baseRecipes.forEach { base ->

                    item() {

                        Card(
                            modifier = Modifier
                                .padding(2.dp)
                                .size(132.dp),
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(2.dp, RB_Orange_50),
                            elevation = 5.dp,
                            backgroundColor = cardBackgroundColor
                        ) {
                            RecipeImageAndInfo(
                                recipe = base,
                                titleFontSize = 14.sp,
                                showTimeAndPortions = false
                            )
                        }

                        Spacer(modifier = Modifier.padding(itemPadding))

                    }
                }


            }

        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height+(2).dp)
                .background(Brush.horizontalGradient(colorList_1_4_2blacks))
        )
    }



}

