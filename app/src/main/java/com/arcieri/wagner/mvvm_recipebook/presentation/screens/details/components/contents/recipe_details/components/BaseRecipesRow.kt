package com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.contents.recipe_details.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Orange_50
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.RecipeImageAndInfo

@Composable
fun BaseRecipesRow(
    catalogViewModel: CatalogViewModel
) {

    var isBasesRecipesVisible by remember { mutableStateOf(true) }

    val recipe = catalogViewModel.currentRecipe
    val recipeList by catalogViewModel.recipeList.collectAsState()

    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(40.dp)
            .fillMaxSize()
            .clickable { isBasesRecipesVisible = !isBasesRecipesVisible },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            modifier = Modifier
                .size(30.dp)
                .rotate(
                    if (isBasesRecipesVisible) {
                        0f
                    } else {
                        -90f
                    }
                ),
            imageVector =
            if (isBasesRecipesVisible) {
                Icons.Default.KeyboardArrowDown
            } else {
                Icons.Default.KeyboardArrowDown
            },
            contentDescription = "Arrow Icon",
            tint = Color(0xFFFFFFFF)
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            text =
            if (recipe!!.baseRecipes.size > 1) {
                "Bases para ${recipe.name} "
            } else {
                "Base para ${recipe.name} "
            },
            color = Color(0xFFFFFFFF),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }

    //TODO check this crap
    //TODO change to getAllRecipes()
    val baseRecipes: MutableList<Recipe> = emptyList<Recipe>().toMutableList()

    for (base in recipe!!.baseRecipes) {

        baseRecipes += recipeList.filter { it.name == base }


    }

    AnimatedVisibility(
        enter = EnterTransition.None,
        exit = ExitTransition.None,
        visible = isBasesRecipesVisible
    ) {
        LazyRow(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .height(140.dp)
        ) {

            item {

                if (baseRecipes.isNotEmpty()) {
                    for (base in baseRecipes) {

                        Card(
                            modifier = Modifier
                                .padding(2.dp)
                                .size(132.dp),
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(2.dp, RB_Orange_50),
                            elevation = 5.dp
                        ) {

                            RecipeImageAndInfo(
                                recipe = base,
                                titleFontSize = 14.sp,
                                showTimeAndPortions = false
                            )

                        }
                    }
                }
            }


        }
    }
}