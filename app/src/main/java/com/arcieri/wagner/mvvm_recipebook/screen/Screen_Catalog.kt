package com.arcieri.wagner.mvvm_recipebook.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arcieri.wagner.mvvm_recipebook.R
import com.arcieri.wagner.mvvm_recipebook.components.RecipeTimeNameRow
import com.arcieri.wagner.mvvm_recipebook.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScreenCatalog(recipeBookViewModel: RecipeBookViewModel) {

    val coroutineScope = rememberCoroutineScope()


    Scaffold() {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            coroutineScope.launch(Dispatchers.Default) {
                recipeBookViewModel.recipeList.collect() { recipes ->
                for (recipe in recipes) {
                    item{
                        RecipeCatalogButton(
                            recipe = recipe,
                            onClick = {
                                /*TODO*/
                            })
                    }
                }

            } }



            item {Divider(Modifier.height(20.dp), Color(0x00000000))}
        }
    }
}


@Composable
fun RecipeCatalogButton(
    recipe: Recipe,
    onClick: () -> Unit
) {

    val recipeImage = remember { mutableStateOf(recipe.image) }

    val noImage = BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.no_image)

    if (recipeImage.value == null) {
        recipeImage.value = noImage
    }

    Divider(
        modifier = Modifier
            .height(20.dp)
            .fillMaxWidth(),
        color = Color(0x00000000)
    )

    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(0.9f)
            .wrapContentHeight()
            .clickable { onClick.invoke() },
        elevation = 15.dp,
        border = BorderStroke(1.dp, Color(0xFFC4C4C4)),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp)
        ) {
            Image(
                modifier = Modifier
                    .padding(0.dp)
                    .height(
                        when (LocalConfiguration.current.orientation) {
                            Configuration.ORIENTATION_PORTRAIT ->
                                (LocalContext
                                    .current
                                    .resources
                                    .displayMetrics
                                    .heightPixels / 12).dp

                            else ->
                                (LocalContext
                                    .current
                                    .resources
                                    .displayMetrics
                                    .heightPixels / 4).dp

                        }

                    )
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop,
                bitmap = recipe.image!!.asImageBitmap(),
                contentDescription = "${recipe.name} image"
            )

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                ) {
                    RecipeTimeNameRow(recipe)
                }



            }


        }


    }
}



@Preview
@Composable
fun ScreenCatalogPreview() {

   val recipeBookViewModel: RecipeBookViewModel = viewModel()


    ScreenCatalog(recipeBookViewModel)

}