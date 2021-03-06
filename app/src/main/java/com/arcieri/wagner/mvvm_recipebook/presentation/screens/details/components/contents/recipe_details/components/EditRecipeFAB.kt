package com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.navigation.Screens
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.catalog.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.*
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
            leftGradientColor =  RB_BlueDarker,
            centerLeftGradientColor = RB_BlueDark,
            centerGradientColor = RB_BlueDark,
            centerRightGradientColor = RB_Blue,
            rightGradientColor = RB_BlueLight,
            borderColor = RB_Transparent,
            elevation = 0.dp,
            iconID = R.drawable.ic_edit_pencil,
            iconSize = 60.dp,
            iconDescription = "EDIT Recipe Button",
            iconColor = RB_White
        )

    }



}