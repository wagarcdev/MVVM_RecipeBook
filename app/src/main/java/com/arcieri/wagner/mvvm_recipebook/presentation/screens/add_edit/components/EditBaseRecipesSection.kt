package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_base_recipes.AddNewBaseRecipeButtonCard
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.catalog.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.RB_White
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.RecipeTimeColumn

@Composable
fun EditBaseRecipesSection(catalogViewModel: CatalogViewModel, itemPadding: Dp) {


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
                .padding(horizontal = 10.dp),
            text = "Bases para ${catalogViewModel.recipe.name}",
            color = Color(0xFF000000),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }

    val baseRecipes: MutableList<Recipe> = emptyList<Recipe>().toMutableList()
    val context = LocalContext.current

    for (base in catalogViewModel.recipe.baseRecipes) {

        baseRecipes += catalogViewModel.recipeList.collectAsState().value.filter { it.name == base }

    }

    LazyRow(
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .height(140.dp)
    ) {

        item {
            AddNewBaseRecipeButtonCard(
                onClick = {

                },
            ) }

        baseRecipes.forEach { base ->

            item() {

                Card(
                    modifier = Modifier
                        .padding(2.dp)
                        .size(132.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(2.dp, RB_White),
                    elevation = 5.dp
                ) {

                    Column(
                        modifier = Modifier
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        base.imageFilepath?.let {
                            AsyncImage(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp))
                                    .width(132.dp)
                                    .height(80.dp),
                                contentScale = ContentScale.Crop,
                                model = it,
                                contentDescription = "${base.name} image"
                            )
                        }

                        Text(
                            text = base.name,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        RecipeTimeColumn(base, Color.LightGray)
                    }
                }

                Spacer(modifier = Modifier.padding(itemPadding))

            }
        }


    }

}

