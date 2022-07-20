package com.arcieri.wagner.mvvm_recipebook.presentation.screens.details.components.contents.recipe_details

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import com.arcieri.wagner.mvvm_recipebook.presentation.widgets.IndexedMethod

@Composable
fun RecipeMethods(recipe: Recipe) {

    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .wrapContentHeight()
            .fillMaxSize(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .height(60.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = "Modo de Preparo",
                color = Color(0xFF000000),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        recipe.recipeMethods.forEachIndexed { index, method ->
            IndexedMethod(index, method, textColor = Color(0xFF222222))
        }
    }




}