package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.EditIngredientsSection
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.EditMethodsSection
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.ImageAndTitleSelectorRowItem
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.catalog.CatalogViewModel


@Composable
fun ScreenAddEditRecipe(
    catalogViewModel: CatalogViewModel,
) {

    val recipe = catalogViewModel.recipe

    val itemPadding = 5.dp

    LazyColumn(
        modifier = Modifier
            .navigationBarsPadding()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(itemPadding),
        contentPadding = PaddingValues(top = 0.dp, bottom = 10.dp)
    ) {

        item { ImageAndTitleSelectorRowItem(recipe, itemPadding) }

        item { EditIngredientsSection(recipe, itemPadding) }

        item { EditMethodsSection(recipe, itemPadding) }

    }
}

