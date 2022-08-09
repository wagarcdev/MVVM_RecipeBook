package com.arcieri.wagner.mvvm_recipebook.presentation.widgets

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.arcieri.wagner.mvvm_recipebook.data.local.CatalogData
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.catalog.CatalogViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AddCatalogOnStart(
    context: Context,
    catalogViewModel: CatalogViewModel
) {
    val defaultDatabase = CatalogData().loadCatalog(context)


    LaunchedEffect(defaultDatabase) {

        launch(Dispatchers.Default) {
            defaultDatabase.forEach { recipe ->
                catalogViewModel.addRecipe(recipe)
            }
        }
    }
}