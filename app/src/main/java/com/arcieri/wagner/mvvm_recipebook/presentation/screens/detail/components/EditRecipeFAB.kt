package com.arcieri.wagner.mvvm_recipebook.presentation.screens.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.navigation.Screens
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.catalog.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.RoundButton

@Composable
fun EditRecipeFAB(
    catalogViewModel: CatalogViewModel,
    recipe: Recipe
) {


    Row(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 0.dp, bottom = 0.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End
    ) {

        RoundButton(
            buttonSize = 100.dp,
            onClick = {

                catalogViewModel.currentRecipe(recipe)

                catalogViewModel.navHostController.navigate(route = Screens.AddEditRecipeScreen.name)

            },
            backgroundColor = Color(0x99FFFFFF),
            borderStroke = 4.dp,
            borderColor = Color(0xFF0000FF),
            elevation = 0.dp,
            iconID = R.drawable.ic_edit_pencil,
            iconSize = 60.dp,
            iconDescription = "EDIT Recipe Button",
            iconColor = Color(0xFF0000FF)
        )

    }



}