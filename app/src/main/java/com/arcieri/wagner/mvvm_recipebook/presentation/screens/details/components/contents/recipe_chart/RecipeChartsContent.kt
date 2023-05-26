package com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.contents.recipe_chart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.model.Ingredient
import com.arcieri.wagner.mvvm_recipebook.model.MeasurementType
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.PieChart
import com.arcieri.wagner.mvvm_recipebook.utils.RandomColors

@Composable
fun RecipeChartsContent(catalogViewModel: CatalogViewModel) {

    val ingredients = catalogViewModel.currentRecipe?.ingredients

    val weightIngredients: MutableList<Ingredient> = arrayListOf()
    val weightIngredientsPoints: MutableList<Float> = arrayListOf()
    val weightIngredientsColors: MutableList<Color> = arrayListOf()

    val liquidIngredients: MutableList<Ingredient> = arrayListOf()
    val liquidIngredientsPoints: MutableList<Float> = arrayListOf()
    val liquidIngredientsColors: MutableList<Color> = arrayListOf()

    val totalIngredients: MutableList<Ingredient> = arrayListOf()
    val totalIngredientsPoints: MutableList<Float> = arrayListOf()
    val totalIngredientsColors: MutableList<Color> = arrayListOf()

    ingredients?.forEach { ingredient ->

        if (ingredient.measurementType == MeasurementType.WEIGHT) {

            ingredient.adjustMeasuringUnits()

            weightIngredientsColors.add(RandomColors().color)
            weightIngredients.add(ingredient)
            weightIngredientsPoints.add(ingredient.weightInGrams!!.toFloat())

            totalIngredientsColors.add(RandomColors().color)
            totalIngredients.add(ingredient)
            totalIngredientsPoints.add(ingredient.weightInGrams!!.toFloat())
        }

        if (ingredient.measurementType == MeasurementType.LIQUID) {

            ingredient.adjustMeasuringUnits()

            liquidIngredientsColors.add(RandomColors().color)
            liquidIngredients.add(ingredient)
            liquidIngredientsPoints.add(ingredient.volumeInMilliliters!!.toFloat())

            totalIngredientsColors.add(RandomColors().color)
            totalIngredients.add(ingredient)
            totalIngredientsPoints.add(ingredient.volumeInMilliliters!!.toFloat())
        }
    }




    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.height(4.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {

            item { catalogViewModel.currentRecipe?.let { IngredientsCharts(it) } }

        }
    }


}

@Composable
private fun IngredientsCharts(recipe: Recipe) {

    val ingredients = recipe.ingredients

    val weightIngredients: MutableList<Ingredient> = arrayListOf()
    val weightIngredientsPoints: MutableList<Float> = arrayListOf()
    val weightIngredientsColors: MutableList<Color> = arrayListOf()

    val liquidIngredients: MutableList<Ingredient> = arrayListOf()
    val liquidIngredientsPoints: MutableList<Float> = arrayListOf()
    val liquidIngredientsColors: MutableList<Color> = arrayListOf()

    val totalIngredients: MutableList<Ingredient> = arrayListOf()
    val totalIngredientsPoints: MutableList<Float> = arrayListOf()
    val totalIngredientsColors: MutableList<Color> = arrayListOf()




    ingredients.forEach { ingredient ->

        if (ingredient.measurementType == MeasurementType.WEIGHT) {

            ingredient.adjustMeasuringUnits()

            weightIngredientsColors.add(RandomColors().color)
            weightIngredients.add(ingredient)
            weightIngredientsPoints.add(ingredient.weightInGrams!!.toFloat())

            totalIngredientsColors.add(RandomColors().color)
            totalIngredients.add(ingredient)
            totalIngredientsPoints.add(ingredient.weightInGrams!!.toFloat())
        }

        if (ingredient.measurementType == MeasurementType.LIQUID) {

            ingredient.adjustMeasuringUnits()

            liquidIngredientsColors.add(RandomColors().color)
            liquidIngredients.add(ingredient)
            liquidIngredientsPoints.add(ingredient.volumeInMilliliters!!.toFloat())

            totalIngredientsColors.add(RandomColors().color)
            totalIngredients.add(ingredient)
            totalIngredientsPoints.add(ingredient.volumeInMilliliters!!.toFloat())
        }
    }

    Column() {

        //weights
        Text(text = "Weight Ingredients")

        PieChart(
            modifier = Modifier,
            isDonut = true,
            progress = weightIngredientsPoints,
            colors = weightIngredientsColors
        )

        //liquids
        Text(text = "Liquid Ingredients")
        PieChart(
            modifier = Modifier,
            isDonut = true,
            progress = liquidIngredientsPoints,
            colors = liquidIngredientsColors
        )


        //TOTAL
        Text(text = "TOTAL of Ingredients")
        PieChart(
            modifier = Modifier,
            isDonut = true,
            progress = totalIngredientsPoints,
            colors = totalIngredientsColors
        )



    }




}
