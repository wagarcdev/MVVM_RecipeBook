package com.arcieri.wagner.mvvm_recipebook.screen

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.components.*
import com.arcieri.wagner.mvvm_recipebook.data.CatalogData
import com.arcieri.wagner.mvvm_recipebook.data.RecipeData
import com.arcieri.wagner.mvvm_recipebook.model.Recipe

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenRecipe(recipe: Recipe) {



    Scaffold(
        modifier = Modifier
            .background(Color(0xFFFFFFFF))
            .fillMaxSize(),
        topBar = { TopRecipeBar(/*navController,*/ recipe) },
        content =  { RecipeMainContent(recipe) },
        floatingActionButton = { EditRecipeFAB() },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = false
    )
}

@Composable
private fun EditRecipeFAB() {

    Row(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 0.dp, bottom = 0.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        RoundButton(
            buttonSize = 60.dp,
            onClick = { },
            backgroundColor = Color(0x99FFFFFF),
            borderStroke = 3.dp,
            borderColor = Color(0xFFFF0000),
            elevation = 0.dp,
            iconID = R.drawable.ic_delete,
            iconSize = 40.dp,
            iconDescription = "DELETE Recipe Button",
            iconColor = Color(0xFFFF0000)
        )

        RoundButton(
            buttonSize = 100.dp,
            onClick = { },
            backgroundColor = Color(0x99FFFFFF),
            borderStroke = 4.dp,
            borderColor = Color(0xFF0000FF),
            elevation = 0.dp,
            iconID = R.drawable.ic_edit_pencil,
            iconSize = 60.dp,
            iconDescription = "EDIT Recipe Button",
            iconColor = Color(0xFF0000FF)
        )

    }



}


@Composable
private fun TopRecipeBar(
//    navController: NavController ,
    recipe: Recipe) {

    Row(
        modifier = Modifier
            .background(Color(0xFFFFFFFF))
            .height(50.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        //BACK Button
        Column() {
            Icon(
                modifier = Modifier
                    .clickable {
//                        navController.popBackStack()
                    }
                    .padding(start = 15.dp)
                    .size(40.dp),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Button",
                tint = Color(0xFF000000)
            )
        }


        Column(
            modifier = Modifier
            .fillMaxWidth(0.9f)
        ) {

            RecipeTimeNameRow(recipe)
        }

    }//Row
}//fun


@Composable
private fun RecipeMainContent(recipe: Recipe) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LoadRecipeImage(recipe)
        
        Spacer(modifier = Modifier.height(4.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {

            if (recipe.baseRecipes != null) {
                item { BaseRecipesRow(recipe) }
            }

            item { IngredientsTable(recipe) }

            item { Divider(modifier = Modifier.height(10.dp), color = Color(0x00FFFFFF)) }

            item { RecipeMethods(recipe) }

            item { Divider(modifier = Modifier.height(100.dp), color = Color(0x00FFFFFF)) }

        }
    }


}

@Composable
private fun BaseRecipesRow(recipe: Recipe) {

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
            if (recipe.baseRecipes?.size!! > 1) {
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

    if (recipe.baseRecipes != null) {
        for (base in recipe.baseRecipes!!) {

            baseRecipes += CatalogData().loadCatalog(context).filter { it.name == base }

        }
    }

    AnimatedVisibility(
        enter = EnterTransition.None,
        exit = ExitTransition.None,
        visible = isBasesRecipesVisible
    ) {
        LazyRow(modifier = Modifier
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

                                baseRecipe.image?.let {
                                    Image(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(5.dp))
                                            .width(132.dp)
                                            .height(80.dp),
                                        contentScale = ContentScale.Crop,
                                        bitmap = it.asImageBitmap(),
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

                                RecipeTime(baseRecipe)
                            }
                        }
                    }
                }
            }


        }
    }
}


@Composable
private fun LoadRecipeImage(recipe: Recipe) {


    val recipeImage = remember { mutableStateOf(recipe.image) }

    val noImage = BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.no_image)

    if (recipeImage.value == null) {
        recipeImage.value = noImage
    }

    Row(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(),
            elevation = 15.dp,
            border = BorderStroke(1.dp, Color(0xFFD5D5D5))
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                bitmap = recipeImage.value!!.asImageBitmap(),
                contentScale = ContentScale.FillWidth,
                contentDescription = "${recipe.name} image"
            )
        }





    }//Row
}//fun


@Composable
private fun IngredientsTable(recipe: Recipe) {

    var isIngredientsVisible by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(40.dp)
            .fillMaxSize()
            .clickable { isIngredientsVisible = !isIngredientsVisible },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            modifier = Modifier
                .size(30.dp)
                .rotate(
                    if (isIngredientsVisible) {
                        0f
                    } else {
                        -90f
                    }
                ),
            imageVector =
            if (isIngredientsVisible) {
                Icons.Default.KeyboardArrowDown
            } else {
                Icons.Default.KeyboardArrowDown
            },
            contentDescription = "Arrow Icon"
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 14.dp),
            text = "Ingredientes",
            color = Color(0xFF000000),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .wrapContentHeight()
            .fillMaxWidth(0.9f)
    ) {

        if (recipe.ingredients != null) {
            for (ingredient in recipe.ingredients!!) {

                ingredient.adjustPortion()

                AnimatedVisibility(visible = isIngredientsVisible) {
                    IngredientItem(
                        ingredient,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }

                AnimatedVisibility(visible = isIngredientsVisible) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(start = 25.dp)
                            .height(1.dp)
                            .background(Color(0xFFE6E6E6))
                    )
                }

                AnimatedVisibility(visible = isIngredientsVisible) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = (Color(0x00000000))
                    )
                }

            }//for ingredient
        }//if


    }//Column
}//fun



@Composable
private fun RecipeMethods(recipe: Recipe) {

    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .wrapContentHeight()
            .fillMaxSize(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .height(60.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = "Modo de Preparo",
                color = Color(0xFF000000),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        recipe.recipeMethods?.forEachIndexed { index, method ->
            IndexedMethod(index, method, textColor = Color(0xFF222222))
        }
    }


    
    
}





@Preview
@Composable
fun ScreenRecipePreview() {
    ScreenRecipe(recipe = RecipeData().loadRecipe(LocalContext.current))
}