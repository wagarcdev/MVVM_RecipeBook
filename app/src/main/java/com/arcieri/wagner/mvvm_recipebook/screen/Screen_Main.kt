package com.arcieri.wagner.mvvm_recipebook.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.navigation.RecipeBookScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenMain(recipeBookViewModel: RecipeBookViewModel) {

    Scaffold() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                modifier = Modifier
                    .size(300.dp)
                    .padding(20.dp),
                painter = painterResource(id = R.drawable.ic_recipe_book_logo),
                contentDescription = "App Logo"
            )





            MenuButton(
                imageID = R.drawable.ic_chef_book,
                imageDescription = "My Recipes Icon",
                text = "My Recipes",
                onClick = {
                    recipeBookViewModel.navHostController.navigate(route = RecipeBookScreens.CatalogScreen.name)
                }
        )

            MenuButton(
                imageID = R.drawable.recipe_icon,
                imageDescription = "New Recipe Button",
                text = "NEW Recipe",
                onClick = {
                    recipeBookViewModel.navHostController.navigate(route = RecipeBookScreens.AddEditRecipeScreen.name+"/NEW")
                }
            )
        }
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 15.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Version 0.0.1")
        }
    }
}

@Composable
fun MenuButton(
    imageID: Int,
    imageDescription: String,
    text: String,
    onClick: () -> Unit
) {
    Divider(
        modifier = Modifier
            .height(20.dp)
            .fillMaxWidth(),
        color = Color(0x00000000)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(100.dp)
            .clickable { onClick.invoke() },
        elevation = 15.dp,
        border = BorderStroke(2.dp, Color(0xFF000000)),
        shape = RoundedCornerShape(15.dp)
    ) {

        Row(
            modifier = Modifier
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop,
                painter = painterResource(
                    id = imageID
                ),
                contentDescription = imageDescription
            )

            Text(
                    modifier = Modifier
                        .padding(6.dp),
            text = text,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
            )
        }


    }
}


@Preview
@Composable
fun ScreenMainPreview() {

    val recipeBookViewModel: RecipeBookViewModel = viewModel()

    ScreenMain(recipeBookViewModel)

}