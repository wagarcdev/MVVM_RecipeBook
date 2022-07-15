package com.arcieri.wagner.mvvm_recipebook.ui.screen.detail.components.detail_screen_content

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.arcieri.wagner.mvvm_recipebook.data.CatalogData
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets.RecipeTimeColumn

@Composable
fun BaseRecipesRow(recipe: Recipe) {

    var isBasesRecipesVisible by remember { mutableStateOf(true) }

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
            contentDescription = "Arrow Icon"
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            text =
            if (recipe.baseRecipes.size > 1) {
                "Bases para ${recipe.name} "
            } else {
                "Base para ${recipe.name} "
            },
            color = Color(0xFF000000),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }

    val baseRecipes: MutableList<Recipe> = emptyList<Recipe>().toMutableList()
    val context = LocalContext.current

    for (base in recipe.baseRecipes) {

        baseRecipes += CatalogData().loadCatalog(context).filter { it.name == base }

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
                    for (baseRecipe in baseRecipes) {

                        Card(
                            modifier = Modifier
                                .padding(2.dp)
                                .size(132.dp),
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(1.dp, Color(0xFFD6D6D6)),
                            elevation = 5.dp
                        ) {

                            Column(
                                modifier = Modifier
                                    .padding(8.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                baseRecipe.imageFilepath?.let {
                                    AsyncImage(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp))
                                            .width(132.dp)
                                            .height(80.dp),
                                        contentScale = ContentScale.Crop,
                                        model = it,
                                        contentDescription = "${baseRecipe.name} image"
                                    )
                                }

                                Text(
                                    text = baseRecipe.name,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                RecipeTimeColumn(baseRecipe, Color.LightGray)
                            }
                        }
                    }
                }
            }


        }
    }
}