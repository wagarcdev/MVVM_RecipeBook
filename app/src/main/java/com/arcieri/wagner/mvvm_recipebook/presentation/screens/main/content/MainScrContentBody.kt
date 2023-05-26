package com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.content

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.navigation.Screens
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.content.body.RecipeCatalogButton
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Black_25
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Black_50
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Black_75
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_Transparent
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.RecipeImageAndInfo
import com.arcieri.wagner.mvvm_recipebook.utils.colorList_1_4_2blacks
import com.arcieri.wagner.mvvm_recipebook.utils.colorList_1_6

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScrContentBody(
    secondaryFontColor: Color,
    recipeDatabase: List<Recipe>,
    buttonsBorderColorNotFocused: Color,
    catalogViewModel: CatalogViewModel,
    navHostController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(RB_Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
        ) {
            CompositionLocalProvider(LocalOverscrollConfiguration provides null) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    item {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
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
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "Recent Visualized",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = secondaryFontColor,
                                textAlign = TextAlign.Start
                            )
                        }
                    }

                    item {

                        val height = 120.dp

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
                                        .padding(horizontal = 8.dp)
                                        .fillMaxWidth()
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
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {

                                    /** TODO Change LazyRow from recipeDatabase for Recent Recipes List */
                                    recipeDatabase.forEach { recipe ->
                                        item {
                                            Card(
                                                modifier = Modifier
                                                    .padding(2.dp)
                                                    .size(height),
                                                shape = RoundedCornerShape(10.dp),
                                                border = BorderStroke(
                                                    2.dp,
                                                    buttonsBorderColorNotFocused
                                                ),
                                                elevation = 5.dp
                                            ) {
                                                RecipeImageAndInfo(
                                                    recipe = recipe,
                                                    titleFontSize = 16.sp,
                                                    showTimeAndPortions = false
                                                )

                                            }
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

                    item {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
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
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "Recipes",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = secondaryFontColor,
                                textAlign = TextAlign.Start
                            )
                        }
                    }



                    items(recipeDatabase.size) { index ->

                        RecipeCatalogButton(
                            recipe = recipeDatabase[index],
                            border = BorderStroke(4.dp, buttonsBorderColorNotFocused),
                            onClick = {

                                catalogViewModel.setRecipe(recipeDatabase[index])

                                navHostController
                                    .navigate(route = Screens.DetailScreen.name)

                            }
                        )
                    }

                    item { Divider(Modifier.height(20.dp), Color(0x00000000)) }
                }
            }
        }


        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Brush.verticalGradient(colorList_1_6))
        )
    }
}