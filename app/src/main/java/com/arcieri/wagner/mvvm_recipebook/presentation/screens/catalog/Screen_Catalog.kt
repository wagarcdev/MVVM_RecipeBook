package com.arcieri.wagner.mvvm_recipebook.presentation.screens.catalog

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.navigation.Screens
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.catalog.components.RecipeCatalogButton

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenCatalog(catalogViewModel: CatalogViewModel) {

    val recipeDatabase = catalogViewModel.recipeList.collectAsState().value

    Scaffold {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.background_image),
                contentDescription = "background",
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(Color(0xCC000000), blendMode = BlendMode.Darken)
            )

        }

        Column(
            modifier = Modifier
                .systemBarsPadding()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            CompositionLocalProvider(LocalOverScrollConfiguration provides  null) {

                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    items(recipeDatabase.size) { index ->

                        RecipeCatalogButton(
                            recipe = recipeDatabase[index],
                            onClick = {

                                catalogViewModel.currentRecipe(recipeDatabase[index])

                                catalogViewModel.navHostController
                                    .navigate(route = Screens.DetailScreen.name)

                            }
                        )
                    }

                    item {Divider(Modifier.height(20.dp), Color(0x00000000))}
                }
            }
        }






    }
}


@Preview
@Composable
fun ScreenCatalogPreview() {

   val recipeBookViewModel: CatalogViewModel = viewModel()


    ScreenCatalog(recipeBookViewModel)

}