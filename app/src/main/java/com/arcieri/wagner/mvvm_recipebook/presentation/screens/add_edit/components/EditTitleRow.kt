package com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_title_and_time_row.EditRecipeNameButtonDisplay
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.add_edit.components.edit_title_and_time_row.EditRecipeTimeButtonDisplay
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.catalog.CatalogViewModel

/**
 *
 *   LazyColumn Items
 *
 * */
@Composable
fun EditTitleAndTimeRow(
    recipeBookViewModel: CatalogViewModel,
    itemPadding: Dp,
    recipeDraft: Recipe
) {

    val recipeDraftTitle = remember { mutableStateOf(recipeDraft.name) }
    val coroutineScope = rememberCoroutineScope()


    Row(
        modifier = Modifier
            .padding(start = 15.dp, top = 10.dp, bottom = 10.dp, end = 20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        EditRecipeNameButtonDisplay(itemPadding, recipeDraftTitle, coroutineScope)

        EditRecipeTimeButtonDisplay(recipeDraft, coroutineScope)
    }


}