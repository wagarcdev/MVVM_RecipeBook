package com.arcieri.wagner.mvvm_recipebook.data

import android.content.Context
import android.graphics.BitmapFactory
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.model.Ingredient
import com.arcieri.wagner.mvvm_recipebook.model.Recipe

class RecipeData {



    fun loadRecipe(context: Context): Recipe {
        return Recipe(
            name = "Gnocchi",
            image = BitmapFactory.decodeResource(context.resources, R.drawable.gnocchi),
            baseRecipes = listOf( "Molho de Tomate da Nonna Maria", "Almôndegas" ) as MutableList<String>,
            ingredients = listOf(
                Ingredient(
                    name = "Batatas Médias",
                    isUnit = true,
                    quantity = 4.0,
                    isLiquid = false,
                    isWeight = false

                ),
                Ingredient(
                    name = "Farinha de Trigo",
                    isLiquid = false,
                    isUnit = false,
                    isWeight = true,
                    weightInGrams = 1000,
                ),
                Ingredient(
                    name = "Água",
                    isLiquid = true,
                    isUnit = false,
                    isWeight = true,
                    volumeInMilliliters = 1200,
                ),
                Ingredient(
                    name = "Sal",
                    isLiquid = false,
                    isUnit = false,
                    isWeight = false,
                    textHowToMeasure = "à gosto"
                ),
                Ingredient(
                    name = "Queijo Parmesão ralado",
                    isLiquid = false,
                    isUnit = false,
                    isWeight = false,
                    textHowToMeasure = "à gosto"
                )

            ) as MutableList<Ingredient>,
            recipeMethods = listOf(
                "Lave bem as batatas e cozinhe-as com a casca.",
                "Quando uma faca perfurar a batata com facilidade é porque está pronta. Retire da panela e espere amornar.",
                "Retire as cascas e amasse as batatas como se fosse fazer um purê. Em uma superfície enfarinhada despeje o amassado e comece a trabalhar a massa acrescentando o sal, aos poucos a farinha de trigo e, por fim, o queijo ralado.",
                "Não existe uma quantidade exata de farinha, basta você saber que a massa precisa ficar firme em formato de bola e sem pregar nas mãos.",
                "Lembre-se de enfarinhar constantemente a superfície onde está trabalhando. Com a massa no ponto, faça cobrinhas mais ou menos da grossura do seu polegar e corte em pedacinhos de 3 cm.",
                "Arredonde as pontas e achate o centro com seu dedo ou com um garfo. Enfarinhe uma travessa e coloque os gnocchi sem encostar uns nos outros e cubra com um pano, deixando em repouso por 1 hora.",
                "Em uma panela larga e funda coloque água para ferver e, somente quando atingir o ponto máximo de fervura, vá colocando os gnocchi um por um.",
                "Fique perto da panela porque eles deverão ser retirados a medida em que começarem a flutuar. Retire um por um com uma escumadeira e só acrescentar por cima o molho de sua preferência."
            ) as MutableList<String>,
            recipeTime = 70

        )//Recipe
    }
}