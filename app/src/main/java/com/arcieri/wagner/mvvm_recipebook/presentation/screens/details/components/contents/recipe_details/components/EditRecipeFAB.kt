package com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.contents.recipe_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.navigation.Screens
import com.arcieri.wagner.mvvm_recipebook.presentation.screens.main.CatalogViewModel
import com.arcieri.wagner.mvvm_recipebook.presentation.ui.theme.*
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.RoundButton

@Composable
fun EditRecipeFAB(
    recipeId: Long,
    catalogViewModel: CatalogViewModel,
    navHostController: NavHostController
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

                navHostController
                    .navigate(
                        route = Screens.AddEditRecipeScreen.name + "/${recipeId}")

            },
            leftGradientColor = RB_OrangeDarker,
            centerLeftGradientColor = RB_OrangeDark,
            centerGradientColor = RB_OrangeDark,
            centerRightGradientColor = RB_Orange,
            rightGradientColor = RB_OrangeLight,
            borderColor = RB_Transparent,
            elevation = 0.dp,
            iconID = R.drawable.ic_edit_pencil,
            iconSize = 60.dp,
            iconDescription = "EDIT Recipe Button",
            iconColor = RB_White
        )

    }



}