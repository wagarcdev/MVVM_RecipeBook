package com.arcieri.wagner.mvvm_recipebook.ui.screen.main

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arcieri.wagner.mvvm_recipebook.ui.screen.catalog.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth.SignInGoogleViewModel
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.auth.SignInGoogleViewModelFactory
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.MainScreenBackgroundContent
import com.arcieri.wagner.mvvm_recipebook.ui.screen.main.components.MainScreenMainMenuContent


@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenMain(
    catalogViewModel: CatalogViewModel,
    signInGoogleViewModel: SignInGoogleViewModel
) {



    /** ADD RECIPES */
//    val context = LocalContext.current
//
//    val defaultDatabase = CatalogData().loadCatalog(context)
//
//
//    LaunchedEffect(defaultDatabase) {
//
//        launch(Dispatchers.Default) {
//            defaultDatabase.forEach { recipe ->
//                catalogViewModel.addRecipe(recipe)
//            }
//        }
//    }


    Row (Modifier.fillMaxSize()) { MainScreenBackgroundContent() }


    Column (
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) { MainScreenMainMenuContent(catalogViewModel, signInGoogleViewModel) }



}


@RequiresApi(Build.VERSION_CODES.R)
@Preview
@Composable
fun ScreenMainPreview() {

    val recipeBookViewModel: CatalogViewModel = viewModel()
    val signInGoogleViewModel: SignInGoogleViewModel = viewModel(
        factory = SignInGoogleViewModelFactory(LocalContext.current.applicationContext as Application)
    )

    ScreenMain(recipeBookViewModel, signInGoogleViewModel)

}