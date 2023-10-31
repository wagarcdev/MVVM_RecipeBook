package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.EditBaseRecipesSection
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.EditIngredientsSection
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.EditMethodsSection
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.ImageTitleAndTimeSelectorRowItem
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.CatalogViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenAddEditRecipe(
    catalogViewModel: CatalogViewModel,
) {
    val itemPadding = 1.dp

    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
        LazyColumn(
            modifier = Modifier
                .navigationBarsPadding()
                .imePadding(),
            verticalArrangement = Arrangement.spacedBy(itemPadding),
            contentPadding = PaddingValues(top = 0.dp, bottom = 10.dp)
        ) {

            item { ImageTitleAndTimeSelectorRowItem(catalogViewModel, itemPadding) }

            item { EditBaseRecipesSection(catalogViewModel, itemPadding) }

            item { EditIngredientsSection(catalogViewModel, itemPadding) }

            item { EditMethodsSection(catalogViewModel, itemPadding) }

        }
    }
}