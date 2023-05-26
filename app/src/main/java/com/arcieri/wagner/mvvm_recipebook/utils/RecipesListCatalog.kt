package com.arcieri.wagner.mvvm_recipebook.utils

import com.arcieri.wagner.mvvm_recipebook.model.Ingredient
import com.arcieri.wagner.mvvm_recipebook.model.MeasurementType
import com.arcieri.wagner.mvvm_recipebook.model.Recipe

val recipeCatalog =
    listOf(
        Recipe(
            name = "Limonada",
            imageFilepath = "https://github.com/wagarcdev/MVVM_RecipeBook/blob/master/blob_images/limonada-suica.jpeg?raw=true",
            ingredients = listOf(
                Ingredient(
                    name = "Limão",
                    measurementType = MeasurementType.UNIT,
                    quantity = 1.0,

                ),
                Ingredient(
                    name = "Água",
                    measurementType = MeasurementType.LIQUID,
                    volumeInMilliliters = 200
                ),
                Ingredient(
                    name = "Açúcar",
                    measurementType = MeasurementType.WEIGHT,
                    weightInGrams = 250
                ),
                Ingredient(
                    name = "Mel",
                    measurementType = MeasurementType.OTHER,
                    textHowToMeasure = "à gosto"
                )
            ) as MutableList<Ingredient>,
            recipeMethods = listOf(
                "Exprema/Extraia o suco de 1 limão em um copo",
                "Adicione açúcar ou mel à gosto",
                "Adicione a água") as MutableList<String>,
            recipeTimeInMinutes = 5,
            portions = 1
        ),//Recipe
        Recipe(
            name = "Gnocchi",
            imageFilepath = "https://github.com/wagarcdev/MVVM_RecipeBook/blob/master/blob_images/gnocchi_pomodoro-780x498.jpg?raw=true",
            baseRecipes = listOf( "Molho de Tomate da Nonna Maria", "Almôndegas" ) as MutableList<String>,
            ingredients = listOf(
                Ingredient(
                    name = "Batatas Médias",
                    measurementType = MeasurementType.UNIT,
                    quantity = 4.0,

                ),
                Ingredient(
                    name = "Farinha de Trigo",
                    measurementType = MeasurementType.WEIGHT,
                    weightInGrams = 1000,
                ),
                Ingredient(
                    name = "Água",
                    measurementType = MeasurementType.LIQUID,
                    volumeInMilliliters = 1200,
                ),
                Ingredient(
                    name = "Sal",
                    measurementType = MeasurementType.OTHER,
                    textHowToMeasure = "à gosto"
                ),
                Ingredient(
                    name = "Queijo Parmesão ralado",
                    measurementType = MeasurementType.OTHER,
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
            recipeTimeInMinutes = 70,
            portions = 2

        ),//Recipe

        Recipe(
            name = "Molho de Tomate da Nonna Maria",
            imageFilepath = "https://github.com/wagarcdev/MVVM_RecipeBook/blob/master/blob_images/molho.jpg?raw=true",
            ingredients = listOf(
                Ingredient(
                    name = "Tomates maduros (sem sementes)",
                    measurementType = MeasurementType.WEIGHT,
                    weightInGrams = 2000

                ),
                Ingredient(
                    name = "Cebola",
                    measurementType = MeasurementType.UNIT,
                    weightInGrams = 1,
                ),
                Ingredient(
                    name = "dentes de Alho (amassados)",
                    measurementType = MeasurementType.UNIT,
                    quantity = 2.0,
                ),
                Ingredient(
                    name = "Azeite",
                    measurementType = MeasurementType.OTHER,
                    textHowToMeasure = "6 colheres"
                ),
                Ingredient(
                    name = "Cominho",
                    measurementType = MeasurementType.OTHER,
                    textHowToMeasure = "à gosto"
                ),
                Ingredient(
                    name = "Manjericão",
                    measurementType = MeasurementType.OTHER,
                    textHowToMeasure = "à gosto"
                )
            ) as MutableList<Ingredient>,
            recipeMethods = listOf(
                "Numa panela, coloque 2 kg de tomate maduro sem sementes e deixe até amolecer.",
                "Passe pelo passador de legumes ou numa peneira.",
                "Leve ao fogo 6 colheres (sopa) de azeite e refogue 1 cebola pequena picada, 2 dentes de alho amassados.",
                "Junte o tomate processado e 1 pitada de cominho.",
                "Cozinhe por 15 min em fogo baixo.",
                "Acerte o sal e finalize com o manjericão a gosto."
            ) as MutableList<String>,
            recipeTimeInMinutes = 50
        ),//Recipe

        Recipe(
            name = "Almôndegas",
            imageFilepath = "https://github.com/wagarcdev/MVVM_RecipeBook/blob/master/blob_images/almondega.webp?raw=true",
            ingredients = listOf(
                Ingredient(
                    name = "Carne Moída",
                    measurementType = MeasurementType.WEIGHT,
                    weightInGrams = 500

                ),
                Ingredient(
                    name = "Xícara de Cebola picada",
                    measurementType = MeasurementType.UNIT,
                    quantity = 1.0,
                ),
                Ingredient(
                    name = "Ovo de Galinha",
                    measurementType = MeasurementType.UNIT,
                    quantity = 1.0,
                ),
                Ingredient(
                    name = "farinha de rosca",
                    measurementType = MeasurementType.WEIGHT,
                    weightInGrams = 150

                ),
                Ingredient(
                    name = "pimenta-do-reino",
                    measurementType = MeasurementType.OTHER,
                    textHowToMeasure = "à gosto"
                ),
                Ingredient(
                    name = "Sal",
                    measurementType = MeasurementType.OTHER,
                    textHowToMeasure = "à gosto"
                )
            ) as MutableList<Ingredient>,

            recipeMethods = listOf(
                "Misture a carne com o ovo, a cebola, o sal, um pouco de azeite de oliva (ou óleo) e a pimenta.",
                "Agregue a farinha até dar o ponto de enrolar as almôndegas.",
                "Faça pequenas bolinhas.",
                "Em uma panela com um pouco de azeite, frite as almôndegas selando-as em fogo alto.",
                "Retire as almôndegas e reserve.",
                "Em outra panela, esquente o molho de tomate em fogo baixo.",
                "Na mesma panela da almôndega, elimine o excesso de azeite e coloque o molho de tomate, colocando as almôndegas para cozinhar por alguns minutos.",
                "Em cerca de 15 minutos as almôndegas estarão totalmente cozidas e o prato estará pronto."
            ) as MutableList<String>,
            recipeTimeInMinutes = 65,
            portions = 4
        )






)