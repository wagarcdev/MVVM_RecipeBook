package com.arcieri.wagner.mvvm_recipebook.ui.screen.add_edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arcieri.wagner.mvvm_recipebook.ui.screen.add_edit.components.EditIngredientsSection
import com.arcieri.wagner.mvvm_recipebook.ui.screen.add_edit.components.EditMethodsSection
import com.arcieri.wagner.mvvm_recipebook.ui.screen.add_edit.components.EditTitleAndTimeRow
import com.arcieri.wagner.mvvm_recipebook.ui.screen.add_edit.components.ImageSelector
import com.arcieri.wagner.mvvm_recipebook.ui.screen.catalog.CatalogViewModel


@Composable
fun ScreenAddEditRecipe(
    catalogViewModel: CatalogViewModel,
) {

    val recipe = catalogViewModel.recipe

    val itemPadding = 5.dp

    LazyColumn(
        Modifier.navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(itemPadding),
        contentPadding = PaddingValues(top = 0.dp, bottom = 10.dp)
    ) {

        item { ImageSelector(recipe) }

        item { EditTitleAndTimeRow(catalogViewModel, itemPadding, recipe) }

        item { EditIngredientsSection(recipe, itemPadding) }

        item { EditMethodsSection(recipe, itemPadding) }

    }
}



@Preview(showBackground = true)
@Composable
fun ScreenAddEditRecipePreview() {

    val recipeList = viewModel<CatalogViewModel>().recipeList.collectAsState().value

   // ScreenAddEditRecipe( recipe = recipeList[0] )





}
