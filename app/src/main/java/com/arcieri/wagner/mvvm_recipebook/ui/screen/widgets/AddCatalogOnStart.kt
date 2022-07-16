package com.arcieri.wagner.mvvm_recipebook.ui.screen.widgets

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.arcieri.wagner.mvvm_recipebook.data.CatalogData
import com.arcieri.wagner.mvvm_recipebook.ui.screen.catalog.CatalogViewModel
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